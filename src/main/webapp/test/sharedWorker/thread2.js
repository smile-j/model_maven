/**
 * Created by Administrator on 2017/8/19.
 */
//onmessage = function(event){
//    var res = event.data+"帅气！";
//    postMessage(res);
//}

onmessage = function (event) {
    var res = event.data + "没骗你哟！";
    postMessage(res);
    close();
}
//worker.port.onmessage = function(e) {
//    var res = e.data;
//    console.log('Message received from worker2');
//    postMessage(res);
//}