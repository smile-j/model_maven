package com.dong.base.test.socket.biochat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.*;

public class Server {

//    ExecutorService service = new ThreadPoolExecutor(5,5,3, TimeUnit.MICROSECONDS,);
    private static List<Socket> allSocketOnline = new ArrayList<>();

    public static void main(String[] args) {

        HashMap<String,String> map = new HashMap<>();
        map.put("","");map.containsKey("");
        
        Hashtable<String,String> hashtable = new Hashtable<>();
        hashtable.put("","");hashtable.containsKey("");

        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            while (true){
                Socket accept = serverSocket.accept();
                allSocketOnline.add(accept);
                new Thread(()->{

                    try {
                        BufferedReader br = new BufferedReader(new InputStreamReader(accept.getInputStream()));
                        String msg ;
                        while ((msg=br.readLine())!=null){
                            for(int i=0;i<allSocketOnline.size();i++){
                                Socket socket = allSocketOnline.get(i);
//                                OutputStream outputStream = socket.getOutputStream();
//                                outputStream.write(msg.getBytes());
//                                outputStream.flush();
//                                outputStream.close();
                                PrintStream ps = new PrintStream(socket.getOutputStream());
                                ps.println(msg);
                                ps.flush();
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                        //下线
                        allSocketOnline.remove(accept);
                    }

                }).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
