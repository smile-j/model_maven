package com.dong.base.test.arith;

import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/1/10.
 */
public class TestArith {

    static int max_x=5;//地图x轴最大值
    static int max_y=5;//地图y轴最大值

    //true为障碍物，false 空地
    static boolean [][] map = new boolean[max_x+2][max_y+1];
    //true 为路过，fasle未路过
    static boolean [][] flag = new boolean[max_x+1][max_y+1];

    //目标特征，x,y从0开始
    static int target_x=3;
    static int target_y=2;

    //球最短路径
     static int min=99999;

    //行驶方向
    static int[][] next ={{1,0},{0,1},{0,-1},{-1,0}};

    private static void dfs(int x,int y,int step){
        if(x==target_x&&y==target_y){
            if(step<min)
                min=step;
            return;
        }
        for(int i=0;i<4;i++){
            int next_x=x+next[i][0];
            int next_y =y+next[i][1];
            if(next_x<0||next_x>=max_x||next_y<0||next_y>=max_y)
                continue;

            if(map[next_x][next_y]==false&&flag[next_x][next_y]==false){
                flag[next_x][next_y]=true;
                dfs(next_x,next_y,step+1);
                flag[next_x][next_y]=false;
            }
        }
    }

    public void shit(){
        map[3][1]=true;
        map[4][1]=true;
        map[2][2]=true;
        map[2][3]=true;
    }

    @Test
    public void testDfs(){
        shit();
        dfs(0,0,0);
        System.out.println(min);
    }

    @Test
    public void test1(){
        int max =9;
        for(int i=1;i<=max;i++){
            for(int j=1;j<=max;j++){
                int result= i+j;
                if(i!=j&&result<10&&i!=result&&j!=result);
                System.out.println(i+"+"+j+"="+(j+1));
            }
        }
    }

    @Test
    public void testHashMap(){
        HashMap map = new HashMap<String,String>();

        map.put("a","aaa");
        map.put("b","bbb");
        map.put("c","ccc");
        System.out.println("   :"+map.toString());

//        System.out.println(10^2);
        System.out.println(Integer.toBinaryString(15));
        System.out.println(Integer.toBinaryString(16));
    }

    @Test
    public void testPrint(){

        final int size = 4;

        printStar(size);

    }

    private static void printStar(int row) {

//        int row = 3;//打印的行数

        for(int i = 0; i < row; i++){
            //循环打印每行前的空格
            for(int j = 0; j < row - (i + 1); j++){
                System.out.print(" ");
            }
            //循环打印每行的*
            for(int j = 0; j < i*2+1; j++){
                System.out.print("*");
            }
            //单行打印完成后换行
            System.out.print("\n");
        }

    }


    @Test
    public void testPrint2(){

        final int size = 4;

        printlf(size);

    }

    public static void printlf(int n){
        int  lineNum = (n<<1);
        int sum=0;
        for (int i=1;i<lineNum;i++){
            if(i-1>=n){
               sum = sum-2;
            }else {
                sum = i +(i-1>0?(i-1):0);
            }
            for(int j=1;j<=sum;j++){
                System.out.print("*");
            }
            System.out.println();
        }

    }


    /**
     * 获取cpu核心数
     */
    @Test
    public void getCpuNub(){
        System.out.println("cpu number is "+Runtime.getRuntime().availableProcessors());
    }

    @Test
    public void testExcutorExcuption() throws InterruptedException {

        ExecutorService pool = Executors.newSingleThreadExecutor();

        pool.execute(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<3;i++){
                    Random random = new Random();
                    int sum =0;
                    for (int j=1;j<random.nextInt(100000);j++){
                        sum+=j;
                    }
                    System.out.println( i+" 次 "+" sum= "+sum+"    "+Thread.currentThread().getName() +"--"+Thread.currentThread().getId());

                }
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {

                double s = 1/0;
                for (int i=0;i<10;i++){
                    System.out.println(Thread.currentThread().getName() +"--"+Thread.currentThread().getId()+
                            "-------2222 "+ new Date());
                }
            }
        });

        pool.execute(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<5;i++){
                    System.out.println(Thread.currentThread().getName() +"--"+Thread.currentThread().getId()+
                            "-------333333333333 "+ new Date());
                }
            }
        });
        pool.shutdown();
        while (!pool.awaitTermination(2,TimeUnit.MINUTES)){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
