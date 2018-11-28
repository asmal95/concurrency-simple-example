package edu.concurrency.elementary;

import java.util.concurrent.TimeUnit;

public class Main {

    static Thread simple = new Thread() {
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Simple 1 executed: " + Thread.currentThread().getName());
        }
    };

    public static void main(String[] args) throws InterruptedException {

        //Thread thread = new Thread();
        //Thread thread = simple;
        Thread thread = new MyThread("new name");
        //Thread thread = new Thread(new MyRunnable());

        //thread.setDaemon(true);
        thread.start();
        //thread.join();

        System.out.println("Main finished: " + Thread.currentThread().getName());

    //thread.join();
// thread.setDaemon(true);
// thread.run();
    }
}

class MyThread extends Thread {

    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("My thread finished: " + Thread.currentThread().getName());
    }
}

class MyRunnable implements Runnable {
    public void run() {
        System.out.println("my runnable finished: " + Thread.currentThread().getName());
    }
}