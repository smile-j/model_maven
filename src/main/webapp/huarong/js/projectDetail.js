
/*function showH(){
	new Vue({
		el:"#showHide",
		data:{
			willShow:false,
			willShow2:false,
			willShow3:false,
		},
		methods:{
			showHide:function(num){
				if(num==1){
					this.willShow=!this.willShow;
				}else if(num==2){
					this.willShow2=!this.willShow2;
				}else if(num==3){
					this.willShow3=!this.willShow3;
				}
			},
		}
	})
}*/

var progress; //进度条
var unt, uit; //选人工具的参数名
var title,id,nextUrl;
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
    //showH();
})


function loadData() {
	$("#second li:first-child").addClass("hit");
	$("#second li").on("click",function(){
		$(this).css({"background-color": "#175499",color: "#FFF"});
		$(this).siblings().css({"background-color": "#FFF",color: "#337AB7"});
	});
	$(".attachments").css("display","none");
	$(".details").css("display","none");

	//判断从代办还是已办点进来的
	var state = getQueryByName("approvalState");
	
 	$("#backArr").on("click",function(){
 		window.location.href = "../index.html?approvalState="+state;
 	});
 	//已办列表页可以高点
 	if(state == 0){
 		$(".content").css("height","13rem");
 		$(".container").css("padding-bottom","0");
 	}

	//进度条
	progress = getBusyOverlay('viewport', 
					{color : 'white',opacity : 1,text : '正在加载，请稍后......',style : 'text-shadow: 0 0 3px black;font-weight:bold;font-size:16px;color:white'}, 
					{color : '#175499',size : 50,type : 'o'}
				);

	title = getQueryByName("title");
	nextUrl = getQueryByName("nextUrl");
	id = getQueryByName("id");
	$(".title").html(title);
	$.post(nextUrl,{
        "reqmethod":"POST",
        "reqparam":"instanceid="+id,
	}).done(success).fail(error).always(function(){
		hidePB();
	});
}

function error() {
	showMsg(badNetwork,close);
};

function hidePB(){
	progress.remove();
}

var attachments;
var details;

function success(msg){


	var data = msg.Data;
	var vm = new Vue({
		el:"#app",
		data:{
			opinion:{
				partment:false,
				law:false,
				bussiness:false
			},
			formInfo:data.FormInfos,
			context : {},
			attachments : [],
			details : [],
			actions : [],
			usedLen : 0,
			normal : "mingxi-content-status",
			handle : "handing",
			unget : "unreceive",
			showForm : false,
			tabSwitch:{
				paper:true,
				files:false,
				transDetail:false
			},
			pageSwitch:{
				detail:true,
				form:false,
				meeting:false
			},
			btnItem:{},
			instanceId:0,
			showPicked:true,
			urls:{},
			docChar:[],
			emergency:[],
			encrypt:[],
			departments:[],
			pickedDepts:[],
			pickElementId:""
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
			toArrow:function(value){		
				value=value.replace("&gt;",">");
				return value;
			},
			plsPick:function(value, arg){
				if(value == ""){
					return "请选择"+arg;
				}else{
					return value;
				}
			}
		},
		mounted:function(){
			this.$nextTick(function(){
				this.createView();
			})
		},
		methods:{
			showHide:function(){
				this.opinion.partment=!this.opinion.partment
			},
			showHide2:function(){
				this.opinion.law=!this.opinion.law
			},
			showHide3:function(){
				this.opinion.bussiness=!this.opinion.bussiness
			},
			createView:function(){
				this.context = data.Context;
				this.attachments = data.Attachments;
				this.details = data.TransInfos;
				this.actions = data.Operations;
				/*
				$('.select-row > select').each(function(i, ele){
					var id = $(ele).attr("id");
					var opt = {
						'date': {
							preset: 'date'
						},
						'select': {
						   preset: 'select'
					   }
					};
					$("#"+id).scroller('destroy').scroller(
						$.extend(opt['select'], {
							theme: "ios7",
							mode: "scroller",
							display: "bottom",
							animate: ""
						})
					);
				});*/
				var _this = this;
				$.post(_this.urls.docChar,{
			        "reqmethod":"POST",
			        "reqparam":"instanceId="+_this.instanceId,
				}).done(function(msg){
					_this.docChar = msg.Data;
				}).fail(error);


				$.post(_this.urls.deptList,{
			        "reqmethod":"POST"
				}).done(function(msg){
					_this.departments = msg.Data;
				}).fail(error);

				this.getDocChar();
				this.getEmergency();
				this.getEncrypt();				
			},
			download:function(data){
				data["transtype"] = "load_pdf"; //请求后台的transtype
				data = $jsonToString(data);
				var params = {
			        "params":data,
			         "callback":"downback()"
			      };
				summer.callService("SummerService.gotoNative", params, false);
			},
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
						"isFirstIn" : true
					},
					"callback" : this.chooseback
				};
				summer.callService("SummerService.gotoNative", params, false);
				console.log("选人");
			},
			chooseback:function(data){
				//选人回调
				var resultStr = data.result;
				resultStr = $stringToJSON(resultStr);
				var result = $stringToJSON(resultStr.data);
				if(!isNull(result)){
					var dealer = $("#"+this.pickElementId);
					dealer.html(result[0].contactName);
					dealer.siblings("input[put='name']").val(result[0].contactName);
					dealer.siblings("input[put='id']").val(result[0].contactCode);
				}
			},
			goToForm:function(item){
				//显示表单页
				this.btnItem = item;
				this.pageSwitch.detail = false;
				this.pageSwitch.form = true;
			},
			pickDept:function(){
				//显示会签页
				this.pageSwitch.meeting = true;
				this.pageSwitch.form = false;
				console.log(this.departments);
			},
			toPickDealer:function(){
				//跳转选人页
				var code = this.btnItem.Code;
				var nextUrl = this.btnItem.Url;
				var param = "instanceId="+ this.instanceId +"&operationCode=" + code + "&" +$("#model-473").serialize();
				
				if($("#Ct_CSign").prop("checked")){
					var deptname = "&Ct_SignDeptName=", userId = "&Ct_SignDeptID=", deptId = "&Ct_SignDeptIDReal=";
					var arr1 = [], arr2 = [], arr3 = [];
					$(".meet-check:checked").each(function(i, ele){
						var value = $(ele).val();
						var uid = $(ele).attr("uid");
						var did = $(ele).attr("did");
						arr1.push(value);
						arr2.push(uid);
						arr3.push(did)
					});
					deptname += arr1.join("\r\n");
					userId += arr2.join(",");
					deptId += arr3.join(",");
					param += deptname + userId + deptId;
				}
				param = decodeURI(param);
				$.post(nextUrl,{
			        "reqmethod":"POST",
			        "reqparam":param,
				}).done(success).fail(error).always(function(){
					hidePB();
				});
				//跳转选人页
				window.location.href = "checkoutPople.html";
			},
			afterPickDept:function(){
				//会签页选择按钮
				this.pageSwitch.form = true;
				this.pageSwitch.meeting = false;
				var _this = this;
				_this.pickedDepts = [];
				$("#depts .meet-check:checked").each(function(i, ele){
					var val = $(ele).val();
					_this.pickedDepts.push({
						dept:val
					})
				});
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
			getDocChar:function(){
				////获取公文待字
				var _this = this;
				$.post(this.urls.docChar,{
			        "reqmethod":"POST",
			        "reqparam":"instanceId="+this.instanceId,
				}).done(function(msg){
					_this.docChar = msg.Data;
				}).fail(error);
			},
			getEmergency:function(){
				//获取缓急
				var _this = this;
				$.post(this.urls.emergency,{
			        "reqmethod":"POST"
				}).done(function(msg){
					_this.emergency = msg.Data;
				}).fail(error);
			},
			getEncrypt:function(){
				//获取密级
				var _this = this;
				$.post(this.urls.encrypt,{
			        "reqmethod":"POST"
				}).done(function(msg){
					_this.encrypt = msg.Data;
				}).fail(error);
			},
			noNeed:function(){
				//不需要会签
				this.showPicked = false;
			},
			yesNeed:function(){
				//需要会签
				this.showPicked = true;
			},
			beChecked:function(event){
				var cbx = $(event.target).children("input[type='checkbox']");
				var ischecked = cbx.prop("checked");
	 			cbx.attr("checked",!ischecked);
			}
			/*pop:function(event){
				var obj = $(event.target).children("select");
				var size = obj.attr("size");
				if (size == 1) {
		            obj.attr("size",obj.children().length);
		        } else {
		            obj.attr("size",1);
		        }
			}*/
		}
	});
	vm.instanceId = id;
	vm.urls = urls
	hidePB();
}

function execute(event){
	var nextParam = event.data;
	var btnUrl = nextParam.btnUrl;
	var code = nextParam.code;

	var formParam = $("#form-win").serialize();
	var ajax = "instanceId="+ id +"&operationCode=" + code + "&" + formParam;
	
	ajax = decodeURI(ajax);
	$.post(btnUrl,{
        "reqmethod":"POST",
        "reqparam":ajax,
	}).done(nextCallback).fail(nextError)
}


function choose(only){
	var staffName = $("#dealer").html();
	var nativeUsers = [];
	if(staffName!="请选择部门领导"){
		nativeUsers.push({contactCode:userId,contactName:staffName});
	}else{
		nativeUsers = [];
	}
	var users = $jsonToString(nativeUsers);
	var params = {
		"params" : {
			"transtype" : "ref_contacts",
			"isselectsingle" : only==1,
			"selectusers" : users,
			"selUserArray" : nativeUsers,
			"isFirstIn" : isFirstIn
		},
		"callback" : chooseback
	};
	summer.callService("SummerService.gotoNative", params, false);
}


function chooseback(data) {
	var resultStr = data.result;
	resultStr = $stringToJSON(resultStr);
	var result = $stringToJSON(resultStr.data);
	if(!isNull(result)){
		var $inputs = $("#argsul~input");
		if($inputs.length > 0){
			$inputs.remove();
		}
		var dealer = $("#dealer");
		dealer.css("color","#000");
		dealer.html(result[0].contactName);
		var userName = result[0].contactName;
		userId = result[0].contactCode;
		var text = "";
		text += "<input type='hidden' name='"+unt+"' value='"+userName+"' />";
		text += "<input type='hidden' name='"+uit+"' value='"+userId+"'/>";
		
		$("#argsform").append(text);
	}
	isFirstIn = false;
};


function nextError() {
	showMsg(badNetwork,close);
};

function nextCallback(msg){
	if(msg == "" || msg == null){
		showMsg(errorMsg,close);
	}else if(msg.Code==0 && (msg.Data == null || !msg.Data)){
		showMsg(msg.Message,backtowaitByLayer);
	}else if(msg.Code==0 && msg.Data != null){

		var data = {};
		data["id"] = id;
		data["msg"] = msg;
		data["title"] = title;
		data["nextUrl"] = nextUrl;
		data = $jsonToString(data);
		$cache.write("cpData",data);
		setTimeout("window.location.href = 'checkoutPople.html'", 500);
		
	}else if(msg.Code==1){
		alert(Message.Message);
	}else if(msg.Code==-1){
		alert("会话无效");
	}
};


