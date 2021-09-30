package com.dong.base.test.concurrent;

public class ErrorContext
{

    private static final String LINE_SEPARATOR = System.getProperty("line.separator", "\n");
    private static final ThreadLocal<ErrorContext> LOCAL = new ThreadLocal<ErrorContext>();

    private String message;

    private ErrorContext()
    {
    }

    public static ErrorContext instance() {
        ErrorContext context = LOCAL.get();
        if (context == null)
        {
            context = new ErrorContext();
//            LOCAL.set(context);
        }
        return context;
    }

    public ErrorContext message(String message)
    {
        this.message = message;
        return this;
    }

    public ErrorContext reset() {

        message = null;
        LOCAL.remove();
        return this;
    }

    @Override
    public String toString() {
        StringBuilder description = new StringBuilder();

        if (this.message != null)
        {
            description.append("### ");
            description.append(this.message);
            description.append(LINE_SEPARATOR);
        }

        return description.toString();
    }

    public static void main(String[] args) {

        Runnable task1 = () -> {

            ErrorContext cxt = ErrorContext.instance();
            cxt.message("info");

            System.out.println(Thread.currentThread().getName()+" "+cxt);
//            cxt.reset();
        };

        Runnable task2 = () -> {
            ErrorContext cxt = ErrorContext.instance();
            cxt.message("error");
            System.out.println(Thread.currentThread().getName()+" "+cxt);
//            cxt.reset();
        };

        ThreadLocal local = new ThreadLocal();

       Thread thread1 = new Thread(task1,"one");
       thread1.start();

        Thread thread2 = new Thread(task2,"two");
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("******************************************end");
        Object obj =LOCAL.get();
        System.out.println("----->"+LOCAL.toString());

    }
}
