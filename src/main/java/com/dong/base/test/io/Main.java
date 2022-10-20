package com.dong.base.test.io;



import com.google.common.collect.Lists;

import java.io.File;
import java.io.FileOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void testNio(){
        new Thread(()->{
            try {
                Selector selector = Selector.open();
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
                serverSocketChannel.bind(new InetSocketAddress(9999));
                serverSocketChannel.configureBlocking(false);
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                AtomicInteger integer = new AtomicInteger(1);
                while (selector.select()>0){
                    integer.addAndGet(1);
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()){
                        SelectionKey next = iterator.next();
                        if(next.isAcceptable()){
                            System.out.println("accept...........");
                            SocketChannel accept = serverSocketChannel.accept();
                            accept.configureBlocking(false);
                            System.out.println(accept.getRemoteAddress()+"上线");
//                            accept.register(selector,SelectionKey.OP_READ|SelectionKey.OP_WRITE);
                            accept.register(selector, SelectionKey.OP_READ);
                        }else if(next.isConnectable()){
                            System.out.println("connection...........");
                        }else if(next.isReadable()){
//                            System.out.println("isread...............");
                            SocketChannel channel = (SocketChannel) next.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            int count = channel.read(buffer);
                            while (count>0){
                                System.out.println(integer.get()+"客户端："+ new String(buffer.array()));
                                count = channel.read(buffer);
                            }
                            channel.write(ByteBuffer.wrap(("hello 我read是server "+format.format(new Date())).getBytes()));
                        }else if(next.isWritable()){
                            System.out.println("iswritable......."+integer.get());
                            SocketChannel channel = (SocketChannel) next.channel();
                            channel.write(ByteBuffer.wrap(("hello 我write是server "+format.format(new Date())).getBytes()));
                        }else {
                            System.out.println("未知。。。。。。。"+next);
                        }
//                        System.out.println("...end");
                        //15.处理完毕后，移除当前事件
                        iterator.remove();
//                        System.out.println("..remove..end");
                    }

                }

            }catch (Exception e){
                e.printStackTrace();
            }
            },"server").start();
       try {
           TimeUnit.SECONDS.sleep(1);
       }catch (Exception e){
           e.printStackTrace();
       }
        new Thread(()->{
            try {
                SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Selector selector = Selector.open();
                SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));
                socketChannel.configureBlocking(false);
                socketChannel.write(ByteBuffer.wrap("hello i am client 01".getBytes()));
                socketChannel.register(selector, SelectionKey.OP_READ);
                //得到username
                String username = Thread.currentThread().getName();
                System.out.println(username + " is ok...");
                new Thread(()->{
                    try {
                        for (int i=0;i<10;i++){
                            TimeUnit.SECONDS.sleep(3);
                            socketChannel.write(ByteBuffer.wrap((username+format.format(new Date())).getBytes()));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }).start();
                while (selector.select()>0){
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()){
                        SelectionKey next = iterator.next();
                        if(next.isReadable()){
                            SocketChannel channel = (SocketChannel) next.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            channel.read(buffer);
                            System.out.println(username+":"+new String(buffer.array(),0,buffer.limit()));
                        }else if(next.isWritable()){
                            SocketChannel channel = (SocketChannel) next.channel();
                            channel.write(ByteBuffer.wrap((username+format.format(new Date())).getBytes()));
                        }
                        iterator.remove();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        },"client 01").start();
        new Thread(()->{
            try {
                SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Selector selector = Selector.open();
                SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));
                socketChannel.configureBlocking(false);
                socketChannel.write(ByteBuffer.wrap("hello i am client 01".getBytes()));
                socketChannel.register(selector, SelectionKey.OP_READ);
                //得到username
                String username = Thread.currentThread().getName();
                System.out.println(username + " is ok...");
                new Thread(()->{
                    try {
                        TimeUnit.SECONDS.sleep(3);
                        for (int i=0;i<10;i++){
                            socketChannel.write(ByteBuffer.wrap((username+format.format(new Date())).getBytes()));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }).start();
                while (selector.select()>0){
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()){
                        SelectionKey next = iterator.next();
                        if(next.isReadable()){
                            SocketChannel channel = (SocketChannel) next.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            channel.read(buffer);
                            System.out.println(username+":"+new String(buffer.array(),0,buffer.limit()));
                        }else if(next.isWritable()){
                            SocketChannel channel = (SocketChannel) next.channel();
                            channel.write(ByteBuffer.wrap((username+format.format(new Date())).getBytes()));
                        }
                        iterator.remove();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        },"client 02").start();

    }

    public static void main(String[] args) throws Exception {
        testNio();
        String str ="abcd";
        System.out.println(str.substring(0,str.length()-1));
        System.out.println("end nio.......................");
        String ss = "abcd";
        System.out.println(ss.substring(0,ss.length()-1));

        List<String> list = Lists.newArrayList("a","b","c");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            if(next.equals("a")){
                iterator.remove();
                continue;
            }
            System.out.println(next);
            if(next.equals("b")){
                iterator.remove();
                continue;
            }
            System.out.println(next);
        }


        /*String ss ="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";
        decoderBase64File(ss,"D:\\11.xlsx","D:\\");

        String tagNo ="huanunion-adx-092701";
        String[] split = tagNo.split("-");
        String dateNo = split[split.length - 1];
        String no = dateNo.substring(4);
        String dataStr = dateNo.substring(0,4);
        String nowDataStr = DateUtils.format(new Date(), "MMdd");
        if(!nowDataStr.equals(dataStr)){
            dataStr = nowDataStr;
        }
        if (StringUtils.isNumeric(no)) {
            int newNum = Integer.parseInt(no) + 1;
//            directPO.setTag(excludeDirectVo.getPlatformType().getPreCode() + "-" + excludeDirectVo.getPlanId() + "-" + (newNum < 10 ? "0" + newNum : newNum));
            System.out.println(dataStr+  (newNum < 10 ? "0" + newNum : newNum));
        }
        System.out.println("===================================================================");

        Date nextDay = DateUtils.getNextDay(new Date(), -10);
        System.out.println(DateUtils.format(nextDay));

        int num = 1234567;
        NumberFormat numberformat = NumberFormat.getNumberInstance();
        System.out.println(numberformat.format(num));

        String str = "12,345.67";
        double d = new DecimalFormat().parse(str).doubleValue();
        System.out.println(String.valueOf(d));*/


    }

    public static void decoderBase64File(String base64Code, String targetPath, String catalogue)

            throws Exception {

        File file = new File(catalogue);

        if(file.exists()==false){

            file.mkdirs();

        }

        byte[] buffer = Base64.getDecoder().decode(base64Code);

        FileOutputStream out = new FileOutputStream(targetPath);

        out.write(buffer);

        out.close();

    }


}
