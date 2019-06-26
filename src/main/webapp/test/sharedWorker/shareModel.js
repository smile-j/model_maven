/**
 * Created by Administrator on 2017/8/20.
 */
var sb;
//onconnect = function(e) {
//    debugger;
//    var port = e.ports[0];
//    port.onmessage = function(e) {
//
//        if(e.data=='get'){
//            port.postMessage(sb);
//        }else{
//            sb=e.data;
//            port.postMessage(sb);
//        }
//    }
//}
onmessage = function (event) {
    var res = event.data + "帅气！";
    postMessage(res);
}