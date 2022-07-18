import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@Slf4j
public class TestNio {


    @Test
    public void  test1(){
        log.debug("aaaaaaaaaaaaaa");
        try {
            FileChannel channel = new FileInputStream("data.txt").getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(2);
            while (true){
                int len = channel.read(byteBuffer);
                if(len==-1){
                    break;
                }
                byteBuffer.flip();//切换读模式
                while (byteBuffer.hasRemaining()){
                    byte b = byteBuffer.get();
                    System.out.println((char)b);
                }
                byteBuffer.clear();//切换写模式  或者byteBuffer.compact()
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
     }


}
