package com.dong.base.test.io.filelock;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.SortedMap;

public class CharsetDemo {

    public static void main(String[] args) throws CharacterCodingException {

        Charset charset = Charset.forName("UTF-8");
        CharsetEncoder charsetEncoder = charset.newEncoder();

        CharBuffer buffer = CharBuffer.allocate(1024);
        buffer.put("你好 hell world!");
        buffer.flip();

        //编码
        ByteBuffer byteBuffer = charsetEncoder.encode(buffer);
        for(int i=0;i<byteBuffer.limit();i++){
            System.out.println(byteBuffer.get());
        }

        System.out.println("---------------------");
        //解码
        byteBuffer.flip();
        CharsetDecoder charsetDecoder = charset.newDecoder();
        CharBuffer decodeBuffer = charsetDecoder.decode(byteBuffer);
        System.out.println(new String(decodeBuffer.array(),0,decodeBuffer.limit()));
//        System.out.println(decodeBuffer.toString());

        //获取Charset所支持的字符编码
        SortedMap<String, Charset> charsetdMap = Charset.availableCharsets();
        charsetdMap.forEach((k,v)->{
            System.out.println(k+"-->"+v.toString());
        });

    }

}
