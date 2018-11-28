package edu.concurrency;

public class Main {

    public static void main(String[] args) throws InterruptedException {


        Thread thread = new Thread(new Task());

        thread.start();

        System.out.println("Main finished: " + Thread.currentThread().getName());
    }
}

class Task implements Runnable {
    public void run() {
        System.out.println("my runnable finished: " + Thread.currentThread().getName());
    }
}
