package com.dong.base.test.io.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 *
 */
public class Server {
    //定义属性
    private Selector selector;
    private ServerSocketChannel ssChannel;
    private static final int PORT = 9999;
    //构造器
    //初始化工作
    public Server() {
        try {
            // 1、获取通道
            ssChannel = ServerSocketChannel.open();
            // 2、切换为非阻塞模式
            ssChannel.configureBlocking(false);
            // 3、绑定连接的端口
            ssChannel.bind(new InetSocketAddress(PORT));
            ssChannel.socket().bind(new InetSocketAddress(PORT));
            // 4、获取选择器Selector
            selector = Selector.open();
            // 5、将通道都注册到选择器上去，并且开始指定监听接收事件
            ssChannel.register(selector , SelectionKey.OP_ACCEPT);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    //监听
    public void listen() {
        System.out.println("监听线程:" + Thread.currentThread().getName());
        try {
            while (selector.select() > 0){
                // 7、获取选择器中的所有注册的通道中已经就绪好的事件
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                // 8、开始遍历这些准备好的事件
                while (it.hasNext()){
                    // 提取当前这个事件
                    SelectionKey sk = it.next();
                    // 9、判断这个事件具体是什么
                    if(sk.isAcceptable()){
                        // 10、直接获取当前接入的客户端通道
                        SocketChannel schannel = ssChannel.accept();
                        // 11 、切换成非阻塞模式
                        schannel.configureBlocking(false);
                        // 12、将本客户端通道注册到选择器
                        System.out.println(schannel.getRemoteAddress() + " 上线 ");
                        schannel.register(selector , SelectionKey.OP_READ);
                        //提示
                    }else if(sk.isReadable()){
                        //处理读 (专门写方法..)
                        readData(sk);
                    }

                    it.remove(); // 处理完毕之后需要移除当前事件
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            //发生异常处理....

        }
    }

    //读取客户端消息
    private void readData(SelectionKey key) {
        //获取关联的channel
        SocketChannel channel = null;
        try {
            //得到channel
            channel = (SocketChannel) key.channel();
            //创建buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = channel.read(buffer);
            //根据count的值做处理
            StringBuilder stringBuilder = new StringBuilder();
            while (count > 0) {
                buffer.flip();
                //把缓存区的数据转成字符串
               stringBuilder.append( new String(buffer.array()));
//                String msg = Charset.forName("UTF-8").decode(buffer);
                count = channel.read(buffer);
            }
            String msg = stringBuilder.toString();
            //输出该消息
            System.out.println("来自客户端---> " + msg);
            //向其它的客户端转发消息(去掉自己), 专门写一个方法来处理
            sendInfoToOtherClients(msg, channel);
        }catch (IOException e) {
            try {
                System.out.println(channel.getRemoteAddress() + " 离线了..");
                e.printStackTrace();
                //取消注册
                key.cancel();
                //关闭通道
                channel.close();
            }catch (IOException e2) {
                e2.printStackTrace();;
            }
        }
    }

    //转发消息给其它客户(通道)
    private void sendInfoToOtherClients(String msg, SocketChannel self ) throws  IOException{
        System.out.println("服务器转发消息中...");
        System.out.println("服务器转发数据给客户端线程: " + Thread.currentThread().getName());
        //遍历 所有注册到selector 上的 SocketChannel,并排除 self
        for(SelectionKey key: selector.keys()) {
            //通过 key  取出对应的 SocketChannel
            Channel targetChannel = key.channel();
            //排除自己
            if(targetChannel instanceof  SocketChannel && targetChannel != self) {
                //转型
                SocketChannel dest = (SocketChannel)targetChannel;
                //将msg 存储到buffer
//                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
//                ByteBuffer buffer = ByteBuffer.wrap(Charset.forName("UTF-8").encode(msg).array());
                //将buffer 的数据写入 通道
//                dest.write(buffer);
                dest.write(Charset.forName("UTF-8").encode(msg));
            }
        }
    }

    public static void main(String[] args) {
        //创建服务器对象
        Server groupChatServer = new Server();
        groupChatServer.listen();
    }
}
