package com.dong.base.test.lock;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2018/2/2.
 */
public class BufferAndLock2 {

    private final Lock lock = new ReentrantLock();

    private final Condition addCondition = lock.newCondition();

    private final Condition subCondition = lock.newCondition();


    private static int num = 0;
    private List<String> lists = new LinkedList<String>();

    public void add() {
        lock.lock();

        try {
            while(lists.size() == 10) {//当集合已满,则"添加"线程等待
                addCondition.await();
            }

            num++;
            lists.add("add Banana" + num);
            System.out.println("The Lists Size is " + lists.size());
            System.out.println("The Current Thread is " + Thread.currentThread().getName());
            System.out.println("==============================");
            this.subCondition.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {//释放锁
            lock.unlock();
        }
    }


    public void sub() {
        lock.lock();

        try {
            while(lists.size() == 0) {//当集合为空时,"减少"线程等待
                subCondition.await();
            }

//            String str = lists.get(0);
//            String str = lists.remove(0);
            String str = (String) ((LinkedList)lists).poll();
            System.out.println("The Token Banana is [" + str + "]");
            System.out.println("The Current Thread is " + Thread.currentThread().getName());
            System.out.println("==============================");
            num--;
            addCondition.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static class AddThread implements Runnable {

        private BufferAndLock2 task;

        public AddThread(BufferAndLock2 task) {
            this.task = task;
        }

        @Override
        public void run() {
          while(true){
              task.add();
          }
        }

    }

    public static class SubThread implements Runnable {

        private BufferAndLock2 task;

        public SubThread(BufferAndLock2 task) {
            this.task = task;
        }

        @Override
        public void run() {
            while (true){
                task.sub();
            }
        }

    }


    public static void main(String[] args) {
        BufferAndLock2 task = new BufferAndLock2();

        AddThread addThread = new AddThread(task);
        SubThread subThread = new SubThread(task);
        for(int i=0;i<4;i++){
            new Thread(addThread,"add线程"+i).start();
        }

        for(int i=0;i<4;i++){
            new Thread(subThread,"sub线程"+i).start();
        }

    }
}
