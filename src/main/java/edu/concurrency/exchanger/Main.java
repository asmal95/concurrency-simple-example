package edu.concurrency.exchanger;

import java.util.concurrent.Exchanger;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Exchanger<String> exgr = new Exchanger<String>();

        Thread thread1 = new Thread(new Task(exgr));
        Thread thread2 = new Thread(new Task(exgr));

        thread1.start();
        thread2.start();

        System.out.println("Main finished: " + Thread.currentThread().getName());
    }
}

class Task implements Runnable {
    private Exchanger<String> ex;

    public Task(Exchanger<String> ex) {
        this.ex = ex;
    }

    public void run() {
        String str = Thread.currentThread().getName();
        try {
            str = ex.exchange(str);
            System.out.println("exchanged: " + str + " My name: "+ Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}