package edu.concurrency.local;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Main {

    public static ThreadLocal<String> local = new InheritableThreadLocal<String>();
    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            local.set("A" + i);
            Thread thread = new Thread(new Task());
            thread.start();
        }
    }

    public static void check() {
        System.out.println(Thread.currentThread().getName() + " checked: " + local.get());
    }
}

class Task implements Runnable {

    public void run() {

        System.out.println(Thread.currentThread().getName() + " : " + Main.local.get());

        Main.local.set(Thread.currentThread().getName() + "_value");
        Main.check();
    }
}