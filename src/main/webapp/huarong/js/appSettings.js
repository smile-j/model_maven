var appSettings = {};

appSettings.ishttps = "false";

// 以下部分只在使用pc端浏览器调试时有用
appSettings.proxyIp = "10.80.38.121";//'10.80.38.121';
appSettings.proxyPort = "8130";//'8060';
// 用友MA的地址，浏览器层的代理完成后将移到原生层去配置
appSettings.proxyUrl = "http://" + appSettings.proxyIp + ":" + appSettings.proxyPort + "/umserver/core/";

//仅浏览器调试时模拟使用
appSettings.token = "2f062eaf982180b6a8b46e936fb05f71203a3bf103f1153cd0ab337c2bae59f9";

// 以下是应用中用到的URL地址，不同环境的地址不一样
appSettings.urlWaitingList = 'http://kmsbak.chamc.com.cn/docapi/doc/workflow/WaittingList';
appSettings.urlFinishedList = 'http://kmsbak.chamc.com.cn/docapi/doc/workflow/FinishedListPage';


//项目立项用到的URL地址
appSettings.urlProjectWaitingList = 'http://projectmanagebak.chamc.com.cn/PMApi/api/workflow/WaittingList';
appSettings.urlProjectFinishedList = 'http://projectmanagebak.chamc.com.cn/PMApi/api/workflow/FinishedList';

//生产环境
//appSettings.urlWaitingList = 'http://kms.chamc.com.cn/docapi/doc/workflow/WaittingList';
//appSettings.urlFinishedList = 'http://kms.chamc.com.cn/docapi/doc/workflow/FinishedList';


//开发环境
//appSettings.urlWaitingList = 'http://10.1.8.81/docapi/doc/workflow/WaittingList';
//appSettings.urlFinishedList = 'http://10.1.8.81/docapi/doc/workflow/FinishedList';

var urls = {};
urls.docChar = "http://kmsbak.chamc.com.cn/docapi/doc/FormItem/GetDocChar";
urls.emergency = "http://kmsbak.chamc.com.cn/docapi/doc/FormItem/GetEmergency";
urls.encrypt = "http://kmsbak.chamc.com.cn/docapi/doc/FormItem/GetEncrypt";
urls.deptList = "http://kmsbak.chamc.com.cn/docapi/doc/ComSend/GetSignDeptList";