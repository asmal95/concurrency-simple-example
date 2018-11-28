package edu.concurrency.interrupt;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {


        Thread thread = new Thread(new LoopTest());
        thread.start();

        TimeUnit.SECONDS.sleep(2);
        thread.interrupt();


        System.out.println("Main finished: " + Thread.currentThread().getName());
    }
}

class SleepTest implements Runnable {
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("OHOHOHOH!");
                return;
            }
            if (Thread.interrupted()) {
                System.out.println("Oh, I was interrupted. Exit!");
                return;
            }
            System.out.println("next");
        }
        System.out.println("Thread " + Thread.currentThread().getName() + " finished");
    }
}


class LoopTest implements Runnable {
    public void run() {
        while (true) {
            if (Thread.interrupted()) {
                System.out.println("Oh, I was interrupted. Exit!");
                return;
            }
            System.out.println("next");
        }
    }
}