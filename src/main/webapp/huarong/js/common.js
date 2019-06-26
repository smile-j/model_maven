//url中的请求参数值，形如：xx=1，返回1
function getQueryByName(name){
	var params = decodeURI(location.search);
	var result = params.match(
			new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
	if (result == null || result.length < 1)
	{
		return "";
	}
	return result[1];
}
//跳转到代办列表页面
function backtowait() {
	window.location.href = "../index.html";
}

//在layer的关闭回调中跳转到代办列表页面
function backtowaitByLayer(index){
	layer.close(index);
	window.location.href = "../index.html";
}

//格式化日期为：17-04-02 17:22的形式，Format无效，暂时截取
function formateDate(date){
	var reg = /\//g;
	date = date.replace(reg,"-");
	var last = date.lastIndexOf(":");
	date = date.substring(2,last);
	return date;
}

/** 判断空值 */
function isNull(data){
	if(!data || null == data  ||  "null" == data ||  "" == data  ||  [] == data ||  "[]" == data ){
		return true;
	}else{
		return false;
	}
}

//使用layer弹框提示消息
function showMsg(msg,sure){
	layer.open({
	    content: msg,
	    btn: "确定",
	    style:"width:60%;color:#000",
	    shadeClose: false,
	    yes:sure
	});
}

//默认的关闭layer回调
function close(index){
	layer.close(index);
}

function translate(data){
	var context = data.Data.Context;
	var common = modelDetailData.common;
	var temp = [];
	for(key in context){
		var zh = common[key];

		if(zh){
			if(key == "SignInfo"){
				var content = "";
				var value = context[key];
				for (var i = 0; i < value.length; i++) {
					content += value[i].DeptName + " : " + value[i].UserName + " " + value[i].SignDate;
				}
				context[key] = content;
			}
			if(key == "CheckInfo"){
				var content = "";
				var value = context[key];
				for (var i = 0; i < value.length; i++) {
					content += value[i].UserName + " : (" + value[i].WriteDate + ")";
				}
				context[key] = content;
			}
			
			temp.push({
				"name":zh,
				"value":context[key]
			});
		}
		
	}
	data.Data.Context = temp;

	var modelId = "model-437";
	var taskId = "task-3";
	var comps = formData[modelId][taskId];
	console.log(comps);
	console.log(JSON.stringify(data.Data.Context));
	return data.Data.Context;
}

function components(){
	var modelId = "model-437";
	var taskId = "task-3";
	formData[modelId][taskId]
}

function hasError(msg){
	if(msg == "" || msg == null){
		showMsg("请求服务器失败",close);
		return true;
	}else if(msg.Code==1){
		showMsg("服务器内部错误",close);
		return true;
	}else if(msg.Code==-1){
		showMsg("登录信息失效,请重新登陆",close);
		return true;
	}
}