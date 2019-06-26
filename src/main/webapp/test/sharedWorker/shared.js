/**
 * Created by Administrator on 2017/8/20.
 */
onconnect = function(e) {
    var port = e.ports[0];
    port.postMessage('Hello World!！！');
}