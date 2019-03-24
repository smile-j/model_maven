package com.dong.base.jdk8.parallel;

import java.util.concurrent.RecursiveTask;

/**
 * Created by Administrator on 2017/11/16.
 *
 * RecursiveAction  没有返回值
 *
 * RecursiveTask有返回值
 *
 */
public class ForkJoinCalculate  extends RecursiveTask<Long> {

    private long start;
    private long end;

    private static final long THRESHOLD=10000;

    public ForkJoinCalculate(long start,long end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length=end -start;

        if(length<THRESHOLD){
            long sum =0;
            for(long i=start;i<=end;i++){
                sum+=i;
            }
            System.out.println(start+"+++++------------------+++++++"+end);
            return sum;
        }else {
            long middle = (start+end)/2;
            ForkJoinCalculate  left = new ForkJoinCalculate(start,middle);
            left.fork();//拆分任务，同时压入线程队列

            ForkJoinCalculate right = new ForkJoinCalculate(middle+1,end);
            right.join();
//            invokeAll(left,right);
            System.out.println(start+"------------------"+end);
            return left.join()+right.join();
        }

    }
}
