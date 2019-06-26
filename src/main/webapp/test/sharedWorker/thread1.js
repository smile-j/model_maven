/**
 * Created by Administrator on 2017/8/19.
 */
//1.基本的
onmessage = function (event) {
    var res = event.data + "帅气！";
    postMessage(res);
}
//2. 扩展的

importScripts('tools.js')
onmessage = function(event){
    var res = handler(event.data);
    postMessage(res);
}
//worker2.port.onmessage = function(e) {
//    var res = e.data;
//    console.log('Message received from worker1');
//    postMessage(res);
//}
//worker.port.onmessage = function(e) {
//    var res = e.data;
//    console.log('Message received from worker1');
//    postMessage(res);
//}