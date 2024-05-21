package com.dong.base.test.concurrent;

/**
 * Created by Administrator on 2018/2/6.
 */
public class TestProductorAndConsumer {

    public static void main(String [] args){
//        Clerk clerk = new Clerk();
//
//        Productor2 productor2 = new Productor2(clerk);
//        Consumer2 consumer2 = new Consumer2(clerk);
//
//       for(int i=0;i<2;i++){
//           new Thread(productor2,"生产者"+i).start();
//           new Thread(consumer2,"消费者"+i).start();
//       }
    }

}

/*//店员
class Clerk {
    private int product = 0;
    private Lock lock = new ReentrantLock();
   //进货
    public synchronized void get() {
        while (product>=1){
            System.out.println("产品已满");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //else{
            System.out.println(Thread.currentThread().getName()+"  :"+ ++product);
            this.notifyAll();
        //}
    }
    //退货
    public synchronized void sale(){
        while (product<=0){
            System.out.println("缺货！");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
      // else {
            System.out.println(Thread.currentThread().getName()+"  :"+ --product);
            this.notifyAll();
        //}
    }
}

class Consumer2 implements Runnable{

    private Clerk clerk;

    public Consumer2(Clerk clerk){
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i=0;i<20;i++){
            this.clerk.sale();
        }
    }
}

class Productor2 implements Runnable{

    private Clerk clerk;

    public Productor2(Clerk clerk){
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i=0;i<20;i++){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.clerk.get();
       }
    }
}*/
