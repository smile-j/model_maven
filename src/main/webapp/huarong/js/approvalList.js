var busyOverlay;
var approvalState = getQueryByName("approvalState") || '1';
var jsonArray=[];
var page = 1;
var workFlowData;
//标示是哪个系统下的单据类型

//m
// 初始化页面事件
document.addEventListener("deviceready", function() {
	console.log("deviceready");
	
	var  myscroll = new iScroll("wrapper", {
					hScrollbar : false,
					vScrollbar : true,
					checkDOMChanges:true,
					bounce:false,
					hideScrollbar:true
				});
				
				
	document.addEventListener("backbutton", function() {
		navigator.app.exitApp();
		//退出app
	}, false);
}, false);
//判断已审批和待审批   默认为带审批   待审批=1  已审批=0
	$(document).on('summerready', function() {
	
	    jsonArray=[];
		bindEvent();
		if (approvalState == '1') {
			getDataOfWaitingList(appSettings.urlWaitingList);
			$('#todoList').addClass("active");
			
			
		} else {
			getDataOfWaitingList(appSettings.urlFinishedList);
			$('#doneHistoryList').addClass("active");
		}
	}); 

function bindEvent() {
	$('#todoList').click(function() {
	    jsonArray=[];
	    approvalState='1';
		getDataOfWaitingList(appSettings.urlWaitingList);
		
		
	});
	$('#doneHistoryList').click(function() {
	     jsonArray=[];
	     approvalState='0';
		getDataOfWaitingList(appSettings.urlFinishedList);
		
		
	});
	
}



function getDataOfWaitingList(url) {
	$('.um-content')[0].style.display = "none";
	showWaiting();
	if(approvalState=='1'){
	var param = {
		"reqmethod" : "POST",
	};
	//$.post(url, param).done(renderWaitingList).fail(errorCallBack);
	$.post(url, param).done(renderWaitingList).fail(errorCallBack).always(function() {
		hideWaiting();
	});
	}else
	{   
	   page = 1;
		$.post(url, {
			"reqmethod" : "POST",
			"reqparam" : "pageNumber=" +page+"&pageSize="+10,
		}).done(renderWaitingList).fail(errorCallBack).always(function() {
			hideWaiting();
		}); 

	  
	}
}

function showWaiting() {
	busyOverlay = getBusyOverlay('viewport', {
		color : 'white',
		opacity : 0.75,
		text : 'viewport: loading...',
		style : 'text-shadow: 0 0 3px black;font-weight:bold;font-size:14px;color:white'
	}, {
		color : '#175499',
		size : 50,
		type : 'o'
	});
	if (busyOverlay) {
		busyOverlay.settext("正在加载......");
	}
}

function hideWaiting() {
	if (busyOverlay) {
		busyOverlay.remove();
		busyOverlay = null;
	}
}

/** 判断空值
 **/
function isNull(data) {
	if (null == data || "null" == data || "" == data || [] == data || "[]" == data) {
		return true;
	} else {
		return false;
	}
}





function getAllData(responseData){
if(responseData.Code == '-1'){
   setBlankBackgroundImage();
   return;
}

 if(approvalState=='1'){
   var data = responseData.Data;
 }else
 {
   var data = responseData.Data.FinishedList;
   workFlowData = data;
 }
	//var jsonArray = [];
	//var InstanceId,
	    //DataUrl;
	for (var i = 0; i < data.length; i++) {
		var Title = data[i].Title;
		if (approvalState == "1") {
			var ArriveTime = data[i].ArriveTime;
			var StepName = data[i].StepName;
		}
		if (approvalState == "0") {
			var ArriveTime = data[i].AcceptTime;
			var StepName = data[i].CurrStatus;
		}
		var TaskName = data[i].TaskName;
		if (isNull(StepName)) {
			StepName = "";
		}
		if (isNull(Title)) {
			Title = "";
		}
		if (isNull(ArriveTime)) {
			ArriveTime = "";
		}
		if (isNull(TaskName)) {
			TaskName = "";
		}
		jsonArray.push({
			"StepName" : StepName,
			"Title" : Title,
			"ArriveTime" : ArriveTime,
			"TaskName" : TaskName
		});
	};
	  //最后将数据放到list里面
	var arrText = doT.template($("#ds").text());
	$("#listview").html('');
	$("#listview").html(arrText(jsonArray));
	//构造控件实例
	//点击事件
	var listview = UM.listview('#listview');
	listview.on("itemClick", function(sender, args) {
		//这里可以处理行点击事件，参数sender即为当前列表实例对象，args对象有2个属性，即rowIndex(行索引)和$target(目标行的jquery对象)
		//alert("点击了"+args.rowIndex);
		var nextTitle = data[args.rowIndex].TaskName;
	    var DataUrl = data[args.rowIndex].DataUrl;
		var  InstanceId = data[args.rowIndex].InstanceId;
		var modelId = data[args.rowIndex].ModelId;
	   type = data[args.rowIndex].SysName;
		
		openNextPage(DataUrl, InstanceId, nextTitle,modelId);
		
	});
	listview.on("pullDown", function(sender) {
	
        			//这是可以编写列表上拉刷新逻辑，参数sender即为当前列表实例对象
      if(approvalState == '1')  {
           jsonArray=[];
          getDataOfWaitingList(appSettings.urlWaitingList);
         
      }else {
         jsonArray=[];
          getDataOfWaitingList(appSettings.urlFinishedList);
        
      }
        	sender.refresh();
      });      
      
      if(approvalState=='0'){
      listview.on("pullUp", function(sender) {
      jsonArray=[];
        			//这是可以编写列表上拉刷新逻辑，参数sender即为当前列表实例对象
        			page++;
        		$.post(appSettings.urlFinishedList, {
			"reqmethod" : "POST",
			"reqparam" : "pageNumber=" +page+"&pageSize="+10,
		}).done(reqFinsinedList);
        sender.refresh();
        });
	}
	
}

function reqFinsinedList(responseData){
    var data = responseData.Data.FinishedList;
    Array.prototype.push.apply(workFlowData,data);
    for (var i = 0; i < workFlowData.length; i++) {
		var Title = workFlowData[i].Title;
		if (approvalState == "1") {
			var ArriveTime = workFlowData[i].ArriveTime;
			var StepName = workFlowData[i].StepName;
		}
		if (approvalState == "0") {
			var ArriveTime = workFlowData[i].AcceptTime;
			var StepName = workFlowData[i].CurrStatus;
		}
		var TaskName = workFlowData[i].TaskName;
		if (isNull(StepName)) {
			StepName = "";
		}
		if (isNull(Title)) {
			Title = "";
		}
		if (isNull(ArriveTime)) {
			ArriveTime = "";
		}
		if (isNull(TaskName)) {
			TaskName = "";
		}
		jsonArray.push({
			"StepName" : StepName,
			"Title" : Title,
			"ArriveTime" : ArriveTime,
			"TaskName" : TaskName
		});
	};
	  //最后将数据放到list里面
	var arrText = doT.template($("#ds").text());
	$("#listview").html('');
	$("#listview").html(arrText(jsonArray));
	//构造控件实例
	//点击事件
	var listview = UM.listview('#listview');
	listview.on("itemClick", function(sender, args) {
		//这里可以处理行点击事件，参数sender即为当前列表实例对象，args对象有2个属性，即rowIndex(行索引)和$target(目标行的jquery对象)
		//alert("点击了"+args.rowIndex);
		var nextTitle = workFlowData[args.rowIndex].TaskName;
	    var DataUrl = workFlowData[args.rowIndex].DataUrl;
		var  InstanceId = workFlowData[args.rowIndex].InstanceId;
		var modelId = data[args.rowIndex].ModelId;
	   type = workFlowData[args.rowIndex].SysName;
		
		openNextPage(DataUrl, InstanceId, nextTitle,modelId);
		
	});
	listview.on("pullDown", function(sender) {
        			//这是可以编写列表上拉刷新逻辑，参数sender即为当前列表实例对象
      if(approvalState == '1')  {
           jsonArray=[];
          getDataOfWaitingList(appSettings.urlWaitingList);
         
      }else {
         jsonArray=[];
          getDataOfWaitingList(appSettings.urlFinishedList);
        
      }
        	sender.refresh();
      });      
      
      if(approvalState=='0'){
      listview.on("pullUp", function(sender) {
       jsonArray=[];
        			//这是可以编写列表上拉刷新逻辑，参数sender即为当前列表实例对象
        			page++;
        		$.post(appSettings.urlFinishedList, {
			"reqmethod" : "POST",
			"reqparam" : "pageNumber=" +page+"&pageSize="+10,
		}).done(reqFinsinedList);
        sender.refresh();
        });
	}

}

//返回值处理
function renderWaitingList(responseData) 
{
  
	
	getAllData(responseData);
}

function errorCallBack() {
	$("#listview").html('');
	showWaiting();
	$('.um-content')[0].style.display = "block";
	$('.um-content').css('background-image', 'url(./img/wangluo_jiazai_tishi2x.png)');
	$('.um-content').css('background-position', 'center');
	$('.um-content').css('background-size', '222px 210px');
	$('.um-content').css('background-repeat', 'no-repeat');
	$('.um-content').css('background-position', 'center center');
	hideWaiting();
}

function refreshPage() {
	$('.um-content')[0].style.display = "none";
	if (approvalState == "1") {
		getDataOfWaitingList(appSettings.urlWaitingList);
	
	}
	if (approvalState == "0") {
		getDataOfWaitingList(appSettings.urlFinishedList);
		
		
	}
}

function setBlankBackgroundImage() {
	$("#listview").html('');
	var $ul = $("<ul></ul>");
    var $li = $("<li>");
    if (approvalState == "1") {
        $li.css({
            'background-image': 'url(./img/noapproval.png)',
            'background-position':'center',
            'background-size':'222px 210px'
        });
    } else {
        $li.css({
            'background-image': 'url(./img/noapprovaled.png)',
            'background-position':'center',
            'background-size':'222px 210px'
        });

    }
            $ul.addClass('ul-container');
        $li.addClass('li-demo')
        $ul.append($li)
	$li.css({
	'background-repeat': 'no-repeat',
	'background-position':'center center'
	});
	$("#listview").html($ul);
	var listview = UM.listview('#listview');
	listview.on("pullDown", function(sender) {
        			//这是可以编写列表上拉刷新逻辑，参数sender即为当前列表实例对象
       if( approvalState == '1'){
        getDataOfWaitingList(appSettings.urlWaitingList);
       
       }
       else{
       getDataOfWaitingList(appSettings.urlFinishedList);
       
       }
        	sender.refresh();
       	});  
}

function openNextPage(DataUrl, InstanceId, nextTitle,modelId) {
  
    var urls = "html/detail"+modelId+".html?title=" + nextTitle + "&id=" + InstanceId + "&approvalState=" + approvalState + "&nextUrl="+DataUrl;
	
	//alert(urls);
	urls = decodeURI(urls);
	window.location.href = urls;
}
// 退出WebView控件
function functionback() {
	var u = navigator.userAgent,
	    app = navigator.appVersion;
	var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1;
	var isIOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/);
	if (isAndroid) {
		navigator.app.exitApp();
	}
	if (isIOS) {
		var pamn = {
			"params" : {
				"transtype" : "exit_back"
			}
		};
		summer.callService("SummerService.gotoNative", pamn, false);
	}
}

//
function getQueryByName(name) {
	var params = decodeURI(location.search);
	var result = params.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
	if (result == null || result.length < 1) {
		return "";
	}
	return result[1];
}

