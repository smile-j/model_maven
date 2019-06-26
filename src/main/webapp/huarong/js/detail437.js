var progress; //进度条
var unt, uit; //选人工具的参数名
var title,id,detailUrl;
var userId;
var isFirstIn = true;
var badNetwork = "请求超时，请检查网络";
var errorMsg = "服务器内部错误";

document.addEventListener("deviceready", function() {
	document.addEventListener("backbutton", function() {
		var state = getQueryByName("approvalState");
		window.location.href = "../index.html?approvalState="+state;
	}, false);
	document.addEventListener("offline", function(){
		layer.open({
		    content: "网络异常, 点击确定刷新页面",
		    btn: "确定",
		    style:"width:60%;color:#000",
		    shadeClose: false,
		    yes:function(index){
		    	layer.close(index);
		    	location.reload();
		    }
		});
	}, false);
}, false);
$(document).on("summerready",function () {
    loadData();
});

function loadData() {

	$("#second li:first-child").addClass("hit");
	$("#second li").on("click",function(){
		$(this).css({"background-color": "#175499",color: "#FFF"});
		$(this).siblings().css({"background-color": "#FFF",color: "#337AB7"});
	});
	//判断从代办还是已办点进来的
	var state = getQueryByName("approvalState");
	
 	$("#backArr").on("click",function(){
 		window.location.href = "../index.html?approvalState="+state;
 	});
 	//已办列表页可以高点
 	if(state == 0){
 		$(".content").css("height","13rem");
 	}

	//进度条
	progress = getBusyOverlay('viewport', 
					{color : 'white',opacity : 1,text : '正在加载，请稍后......',style : 'text-shadow: 0 0 3px black;font-weight:bold;font-size:16px;color:white'}, 
					{color : '#175499',size : 50,type : 'o'}
				);

	title = getQueryByName("title");
	detailUrl = getQueryByName("nextUrl");
	id = getQueryByName("id");
	$(".title").html(title);
	$.post(detailUrl,{
        "reqmethod":"POST",
        "reqparam":"instanceid="+id,
	}).done(success).fail(error).always(function(){
		hidePB();
	});
}

function error() {
	$("div[error='hide']").css("display","none");
	showMsg(badNetwork,close);
};

function hidePB(){
	progress.remove();
}

function success(msg){
	if(hasError(msg)){
		return;
	}

	var data = msg.Data;
	data["DocYear"] = 2017;
	var flowInfo = data.WorkFlowInfo;
	var model = flowInfo.ModelId;
	var task = flowInfo.TaskId;
	var taskComponents = formData2["model-"+model]["task-"+task];

	var vm = new Vue({
		el:"#app",
		data:{
			//4组数据
			context : {},
			attachments : [],
			details : [],
			actions : [],
			usedLen : 0, //按钮已使用长度
			//流转的3中状态
			normal : "mingxi-content-status",
			handle : "handing",
			unget : "unreceive",
			showForm : false,
			//3个页签的显示切换
			tabSwitch:{
				paper:true,
				files:false,
				transDetail:false
			},
			//详情，表单，会签页面切换
			pageSwitch:{
				detail:true,
				form:false,
				meeting:false
			},
			//已点击按钮数据
			btnItem:{},
			instanceId:0,
			//显示会签单位
			showPicked:true,
			showAddPicked:false,
			//公文待字，缓急，密级,会签单位url
			urls:{},
			//返回的公文待字，缓急，密级，会签单位
			departments:[],
			//默认选择需要会签
			checkSign:true,
			//已选择的会签单位
			pickedDepts:[],
			//选择会签单位后的提交参数
			pickedInput:{},
			//增加会签：选择会签单位后的提交参数
			addPickedInput:{},
			//从哪个元素触发的选人
			pickElementId:"",
			//流程中要显示的表单组件
			taskComponents:{},
			components:{},//每个按钮显示不同的表单
			//选人是否是第一次进
			isFirstIn:true
		},
		filters:{
			translateCheck:function(value){
				return messageType[value];
			},
			fileIcon:function(value){
				return "../img/"+value+".png";
			},
			formatDate:function(value){
				var reg = /\//g;
				value = value.replace(reg,"-");
				var last = value.lastIndexOf(":");
				value = value.substring(2,last);
				return value;
			},
			plsPick:function(value, arg){
				if(value == ""){
					return "请选择"+arg;
				}else{
					return value;
				}
			},
			grayColor:function(value){
				if(value == ""){
					return "color:#DDD";
				}else{
					return "color:#337AB7";
				}
			}
		},
		created:function(){
			$("div[error='hide']").css("display","block");
		},
		mounted:function(){
			this.$nextTick(function(){
				this.createView();
			})
		},
		computed:{
			hitLeader:function(){
				return {hintFont:this.context.Ct_LeaderName==""};
			},
			hitManager:function(){
				return {hintFont:this.context.Ct_ManagerName==""};
			}
		},
		methods:{
			createView:function(){
				this.context = data.Context;
				this.attachments = data.Attachments;
				this.details = data.TransInfos;
				this.actions = data.Operations;
				if(this.taskId < 9){
					delete this.context.LeaderSignInfo;
				}

				//倒序流转明细
				this.reverseTrans();
			},
			reverseTrans:function(){
				var data = this.details;
				var i = 0, j = data.length-1;
				var temp = {};
				while(i < j){
					temp = data[i];
					data[i] = data[j];
					data[j] = temp;
					i++;
					j--;
				}
			},
			download:function(data){
				//附件下载
				data["transtype"] = "load_pdf"; //请求后台的transtype
				var params = {
			        "params":data,
			        "callback" : this.downback
			      };
				summer.callService("SummerService.gotoNative", params, false);
			},
			downback:function(){
				console.log("附件打开成功");
			},
			/* S= 三个页签切换*/
			paper:function () {
				this.tabSwitch.paper = true;
				this.tabSwitch.files = false;
				this.tabSwitch.transDetail = false;
				
				$("#paper").css({"background-color": "#175499",color: "#FFF"});
				$("#files").css({"background-color": "#FFF",color: "#337AB7"});
				$("#details").css({"background-color": "#FFF",color: "#337AB7"});
			},
			files:function () {
				this.tabSwitch.paper = false;
				this.tabSwitch.files = true;
				this.tabSwitch.transDetail = false;

				$("#paper").css({"background-color": "#FFF",color: "#337AB7"});
				$("#files").css({"background-color": "#175499",color: "#FFF"});
				$("#details").css({"background-color": "#FFF",color: "#337AB7"});
			},
			transInfo:function () {
				this.tabSwitch.paper = false;
				this.tabSwitch.files = false;
				this.tabSwitch.transDetail = true;

				$("#paper").css({"background-color": "#FFF",color: "#337AB7"});
				$("#files").css({"background-color": "#FFF",color: "#337AB7"});
				$("#details").css({"background-color": "#175499",color: "#FFF"});
			},
			/* E= 三个页签切换*/
			isUndefine:function(data){
				//判空
				if(!data || null == data  ||  "null" == data ||  "" == data  ||  [] == data ||  "[]" == data ){
					return true;
				}else{
					return false;
				}
			},
			calc:function(index){
				//计算按钮长度
				var length = this.actions.length;
				if(length > 0){
					return {width:"calc("+100/length+"%)"};
				}
			},
			pickStaff:function(id){
				//选办理人
				this.pickElementId = id;
				var staffName = $("#"+id).html();
				var userId = $("#"+id).attr("uid");
				var nativeUsers = [];
				if(staffName.indexOf("请选择") != -1){
					nativeUsers.push({contactCode:userId,contactName:staffName});
				}else{
					nativeUsers = [];
				}
				var users = $jsonToString(nativeUsers);
				var params = {
					"params" : {
						"transtype" : "ref_contacts",
						"isselectsingle" : true,
						"selectusers" : users,
						"selUserArray" : nativeUsers,
						"isFirstIn" : this.isFirstIn
					},
					"callback" : this.chooseback
				};
				summer.callService("SummerService.gotoNative", params, false);
				//console.log("选人");
			},
			chooseback:function(data){
				//选人回调
				var resultStr = data.result;
				resultStr = $stringToJSON(resultStr);
				var result = $stringToJSON(resultStr.data);
				if(!isNull(result)){
					var dealer = $("#"+this.pickElementId);
					this.context[this.pickElementId] = result[0].contactName;
					//dealer.html(result[0].contactName); 改用VUE刷新
					var uname = dealer.siblings("input[put='name']");
					var uid = dealer.siblings("input[put='id']");
					uname.val(result[0].contactName);
					uid.val(result[0].contactCode);
					// S=防止vue组件刷新回默认值
					var name1 = uname.attr("name");
					var name2 = uid.attr("name");
					this.context[name1] = result[0].contactName;
					this.context[name2] = result[0].contactCode;
					// E=防止vue组件刷新回默认值
					this.isFirstIn = false;
				}
			},
			goToForm:function(item){
				//显示表单页
				this.btnItem = item;    
				var code = item.Code;
				this.components = this.taskComponents[code];
				//会签人类型决定是否显示会签意见
				var signerType = this.context.SignUserType;
				//FileManager不需要显示
				if(!isNull(signerType) && signerType == "FileManager"){
					delete this.components.signContent;
					data.Context.SignContent = null;
				}
				if(jQuery.isEmptyObject(this.components)){
					this.toPickDealer();
				}else{
					if(!isNull(this.components)){
						//处理会签部门数据
						var ctSignDeptName = this.context.Ct_SignDeptName;
						this.pickedInput["deptname"] = ctSignDeptName;
						this.pickedInput["userId"] = this.context.Ct_SignDeptID;
						this.pickedInput["deptId"] = this.context.Ct_SignDeptIDReal;
						if(!isNull(ctSignDeptName) && !this.components.addSign){
							//暂时是rn，后台修复后是\r\n
							var deptnames = ctSignDeptName.split("rn");
							this.pickedDepts = [];
							for (var i = 0; i < deptnames.length; i++) {
								this.pickedDepts.push({
									dept:deptnames[i]
								});
							}
						}

						this.checkSign = this.context.Ct_CSign == "Y";
						
						//获取会签单位数据
						var _this = this;
						$.post(_this.urls.deptList,{
							"reqmethod":"POST"
						}).done(function(msg){
							_this.departments = msg.Data;
						}).fail(error);
					}
					this.pageSwitch.detail = false;
					this.pageSwitch.form = true;
				}
			},
			//计算json对象元素个数
			getJsonLength:function(data){
				var size = 0, key;
				for(key in data){
					if(data.hasOwnProperty(key))
						size++;
				}
				return size;
			},
			pickDept:function(){
				//显示会签页
				this.pageSwitch.meeting = true;
				this.pageSwitch.form = false;
			},
			toPickDealer:function(){
				//跳转选人页
				var _this = this;
				var code = this.btnItem.Code;
				var nextUrl = this.btnItem.Url;
				
				if(this.pageSwitch.form){
					var idNeed = $("#Ct_CSign").prop("checked") || $("#Ct_CAddSign").prop("checked");
					if(idNeed){
						var checkedDepts = $(".meet-check:checked");
						if(checkedDepts.length>0){
							var arr1 = [], arr2 = [], arr3 = [];
							$(".meet-check:checked").each(function(i, ele){
								var value = $(ele).val();
								var uid = $(ele).attr("uid");
								var did = $(ele).attr("did");
								arr1.push(value);
								arr2.push(uid);
								arr3.push(did)
							});
							_this.pickedInput["deptname"] = arr1.join("\r\n");
							_this.pickedInput["userId"] = arr2.join(",");
							_this.pickedInput["deptId"] = arr3.join(",");
						}
						if(_this.components.meeting){
							if(_this.isUndefine(_this.pickedInput.deptname)){
								showMsg("请选择会签单位",close);
								return;
							}
						}
						if(_this.components.addSign){
							if(_this.isUndefine(_this.addPickedInput.deptname)){
								showMsg("请选择会签单位",close);
								return;
							}
						}
					}
					if(_this.components.manager){
							if(_this.isUndefine(_this.context.Ct_ManagerID)){
								showMsg("请选择处室负责人",close);
								return;
							}
						}
						if(_this.taskId == 4){
							if(_this.isUndefine(_this.context.Ct_LeaderID)){
								showMsg("请选择主办部门领导",close);
								return;
							}
						}
				}

				var forms = $("#model-473").serialize();
				var param = "instanceId="+ this.instanceId +"&operationCode=" + code + "&" + forms;
				param = decodeURI(param);
				$.post(nextUrl,{
			        "reqmethod":"POST",
			        "reqparam":param,
				}).done(function(msg){
					if(msg == "" || msg == null){
						showMsg(errorMsg,close);
					}else if(msg.Code==0 && isNull(msg.Data)){
						showMsg(msg.Message,backtowaitByLayer);
					}else if(msg.Code==0 && msg.Data != null){
						var data = {};
						data["id"] = _this.instanceId;
						data["msg"] = msg;
						data["title"] = title;
						data["detailUrl"] = detailUrl;
						data = $jsonToString(data);
						$cache.write("cpData",data);
						window.location.href = "checkoutPople.html";
						//setTimeout("window.location.href = 'checkoutPople.html'", 500);
					}else if(msg.Code==1){
						showMsg(msg.Message,close);
					}else if(msg.Code==-1){
						showMsg("登录信息失效,请重新登陆",close);
					}
				}).fail(function(){
					showMsg(badNetwork,close);
				});
			},
			afterPickDept:function(){
				//会签页选择按钮
				this.pageSwitch.form = true;
				this.pageSwitch.meeting = false;
				var _this = this;
				_this.pickedDepts = [];


				var arr1 = [], arr2 = [], arr3 = [];
				$("#depts .meet-check:checked").each(function(i, ele){
					var val = $(ele).val();
					var uid = $(ele).attr("uid");
					var did = $(ele).attr("did");
					
					arr1.push(val);
					arr2.push(uid);
					arr3.push(did)
					_this.pickedDepts.push({
						dept:val
					})
				});
				_this.pickedInput["deptname"] = arr1.join("\r\n");
				_this.pickedInput["userId"] = arr2.join(",");
				_this.pickedInput["deptId"] = arr3.join(",");
				_this.addPickedInput["deptname"] = arr1.join("\r\n");
				_this.addPickedInput["userId"] = arr2.join(",");
				_this.addPickedInput["deptId"] = arr3.join(",");
			},
			backForm:function(){
				//会签页返回按钮
				this.pageSwitch.detail = false;
				this.pageSwitch.form = true;
				this.pageSwitch.meeting = false;
			},
			backDetail:function(){
				//表单页返回按钮
				this.pageSwitch.detail = true;
				this.pageSwitch.form = false;
			},
			pickDocChar:function(event,field){
				////获取公文待字
				var target = $(event.target);
				this.scollCommon(target,field,"DocChar");
			},
			pickDocYear:function(event,field){
				var _this = this;
				var target = $(event.target);
				var years = ["2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022"];
				var charsData = years.map(function(name,idx){
						return {
							text:years[idx],
							value:years[idx]
						};
					});
				var picker = new WheelPicker({
				    title: "",
				    data: [charsData],
				    value:["2017"],
				    onSelect: function(selected) {
				    	var value = selected[0].value;
				    	var text = selected[0].text;
				    	_this.context.DocYear = value;
				    	target.children("input[type='hidden']").val(value);
				    	target.children(".arrow").html(text);
				    }
				});
				picker.show();
			},
			pickEmergency:function(event,field){
				//获取缓急
				var target = $(event.target);
				this.scollCommon(target,field,"Emergency");
			},
			pickEncrypt:function(event,field){
				//获取密级
				var target = $(event.target);
				this.scollCommon(target,field,"Encrypt");
			},
			scollCommon:function(target,field,tolerate){
				if(target[0].tagName == "P"){
					target = target.parent();
				}
				var _this = this;
				//如果第一次进，没有缓存，请求数据
				if(isNull(_this[field])){
					$.post(_this.urls[field],{
				        "reqmethod":"POST",
				        "reqparam":"instanceId="+this.instanceId,
					}).done(function(msg){
						var data = msg.Data;
						_this[field] = data;
						var charsData = data.map(function(name,idx){
							return {
								text:name.Name,
								value:name.ID
							};
						});
						var picker = new WheelPicker({
						    title: "",
						    data: [charsData],
						    value:[_this.context[tolerate]],
						    onSelect: function(selected) {
						    	var value = selected[0].value;
						    	var text = selected[0].text;
						    	_this.context[tolerate] = value;
						    	target.children("input[type='hidden']").val(value);
						    	target.children(".arrow").html(text);
						    }
						});
						picker.show();
					}).fail(function(){
						showMsg(badNetwork,close);
					});
				}else{
					//有缓存
					var charsData = _this[field].map(function(name,idx){
						return {
							text:name.Name,
							value:name.ID
						};
					});
					var picker = new WheelPicker({
					    title: "",
					    data: [charsData],
					   	value:[_this.context[tolerate]],
					    onSelect: function(selected) {
					    	var value = selected[0].value;
					    	var text = selected[0].text;
					    	_this.context[tolerate] = value; //必须更新vue实例
					    	target.children("input[type='hidden']").val(value);
					    	target.children(".arrow").html(text);
					    }
					});
					picker.show();
				}
			},
			noNeed:function(){
				//不需要会签
				this.showPicked = false;
				this.showAddPicked = false;
				this.checkSign = false;
			},
			yesNeed:function(){
				//需要会签
				this.showPicked = true;
				this.showAddPicked = true;
				this.checkSign = true;
			},
			beChecked:function(event){
				//会签单位的选中事件
				var target = $(event.target);
				if(target[0].tagName == "P" ||target[0].tagName  == "SPAN"){
					target = target.parent();
				}
				var cbx = target.children("input[type='checkbox']");
				var ischecked = cbx.prop("checked");
	 			cbx.prop("checked",!ischecked);
			},
			checkedStatus:function(value){
				if(this.components.meeting){
					//会签页面会签单位默认选中的实现
					return this.pickedInput.deptId.indexOf(value)>-1;
				}else{
					return false;
				}
			}
		}
	});
	vm.instanceId = id;
	vm.urls = urls;
	vm.taskId = task;
	vm.taskComponents = taskComponents;
	hidePB();
}