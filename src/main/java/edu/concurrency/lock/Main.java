package edu.concurrency.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static String resource = "1";
    public static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Task());
            thread.start();
        }


        System.out.println("Main finished: " + Thread.currentThread().getName());
    }
}

class Task implements Runnable {
    public void run() {
        try {
            String name = Thread.currentThread().getName();
            if (Main.lock.tryLock(10, TimeUnit.SECONDS)) {
            //if (Main.lock.tryLock()) {
                try {
                    System.out.println(name + " got lock");
                    Main.resource += "1";
                    TimeUnit.SECONDS.sleep(3);
                } finally {
                    Main.lock.unlock();
                }
            } else {
                System.out.println(name + " can't get lock");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println("my runnable finished: " + Thread.currentThread().getName());
    }
}