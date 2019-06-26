import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/9/26.
 */
public class TestBase {

    @Test
    public void parallelTest() throws InterruptedException {
        Integer[] intArray = {1, 2,9, 3, 4, 5, 6, 7, 8};
        List<Integer> listOfIntegers = new ArrayList<Integer>(Arrays.asList(intArray));
        List<Integer> parallelStorage = new ArrayList<>();//Collections.synchronizedList(new ArrayList<>());
        listOfIntegers
                .parallelStream()
                        // Don't do this! It uses a stateful lambda expression.
                .map(e -> {
//                    System.out.println("element:"+e);
                    parallelStorage.add(e);
                    return e;
                })
                .forEachOrdered(e -> System.out.print(e + " "));
        System.out.println();
        parallelStorage
                .stream()
                .forEachOrdered(e -> System.out.print(e + " "));
        System.out.println();
        System.out.println("Sleep 5 sec");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("num:"+parallelStorage.size());
        parallelStorage
                .stream()
                .forEach(e -> System.out.print(e + " "));
        System.out.println();
        System.out.println("2num:"+parallelStorage.size());
        parallelStorage.stream()
                        .forEach(e->System.out.print(e + " "));

    }

    public void testlambdaFile(){

        File file = new File("");
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return false;
            }
        };

        File file2 = new File("");
        FileFilter fileFilter2 =(File f) -> f.isDirectory();


    }

}
