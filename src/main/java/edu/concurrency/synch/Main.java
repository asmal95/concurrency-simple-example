package edu.concurrency.synch;

import java.util.concurrent.TimeUnit;

public class Main {


    public static Message message = new Message();
    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            new Thread(new SynchronizedTest()).start();
        }

        System.out.println("Main finished: " + Thread.currentThread().getName());
    }
}

class SynchronizedTest implements Runnable {
    public void run() {

        synchronized (Main.message) {
            try {
                Main.message.setText(Thread.currentThread().getName());
                System.out.println("Thread " + Thread.currentThread().getName() + " reached the mutex.");
                TimeUnit.SECONDS.sleep(5);
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

class Message {
    String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}