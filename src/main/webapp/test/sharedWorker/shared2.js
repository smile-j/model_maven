/**
 * Created by Administrator on 2017/8/20.
 */

onconnect = function(e) {
   console.log(e+':---:'+e.message);
    var port = e.ports[0];
    port.postMessage('Hello World2!');
    port.onmessage = function(e) {
        console.log(e+':---:'+ e.message);
        port.postMessage('pong2'); // not e.ports[0].postMessage!
        // e.target.postMessage('pong'); would work also
    }
}