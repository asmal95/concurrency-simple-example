package edu.concurrency.methods;

import java.util.concurrent.TimeUnit;

public class Main {

    public static final Object monitor = new Object();

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            new Thread(new SynchronizedTest()).start();
        }

        for (int i = 0; i < 10; i++) {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("Awake some thread");

            synchronized (monitor) {
                Main.monitor.notify();
            }
        }

        System.out.println("Main finished: " + Thread.currentThread().getName());
    }
}

class SynchronizedTest implements Runnable {
    public void run() {

        synchronized (Main.monitor) {
            try {
                System.out.println("Thread " + Thread.currentThread().getName() + " reached the mutex. And will wait...");
                Main.monitor.wait();
                System.out.println("Thread " + Thread.currentThread().getName() + " awoken!");

            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        System.out.println("finished: " + Thread.currentThread().getName());
        System.out.println("===============");
        System.out.println();
    }
}