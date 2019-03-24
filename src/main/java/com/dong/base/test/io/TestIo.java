package com.dong.base.test.io;

import org.junit.Test;

import java.io.*;

/**
 * Created by Administrator on 2018/6/6.
 */
public class TestIo {



    @Test
    public void test1(){
        InputStream is = null;
        BufferedInputStream bis = null;
        try {
//            InputStream is = new FileInputStream("hello.txt");
            is = this.getClass().getResourceAsStream("hello.txt");
            bis = new BufferedInputStream(is);
//            Reader reader=new FileReader("");//            byte [] res = new byte[512];
//            Reader reader = new InputStreamReader(is);
            char [] res = new char[5];
            byte [] bytes = new byte[5];
            int  len = 0;
//            while ((len = reader.read(res))>0){
            while ((len = bis.read(bytes))>0){//bis.available()
                System.out.print(new String(bytes,"gbk"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try{
                if(is!=null){
                    is.close();
                }
                if(bis!=null){
                    bis.close();
                }
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }

    }

}
