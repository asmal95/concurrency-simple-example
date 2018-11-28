package edu.concurrency.deadlock;

public class Main {

    public static void main(String[] args) throws InterruptedException {


        Thread threadA = new Thread(new TaskA());
        Thread threadB = new Thread(new TaskB());

        threadA.start();
        threadB.start();

        System.out.println("Main finished: " + Thread.currentThread().getName());
    }
}

class TaskA implements Runnable {
    public void run() {
        synchronized (TaskA.class) {
            System.out.println("Thread 1: Holding lock 1...");

            try { Thread.sleep(10); }
            catch (InterruptedException e) {}
            System.out.println("Thread 1: Waiting for lock 2...");

            synchronized (TaskB.class) {
                System.out.println("Thread 1: Holding lock 1 & 2...");
            }
        }
    }
}


class TaskB implements Runnable {
    public void run() {
        synchronized (TaskB.class) {
            System.out.println("Thread 2: Holding lock 2...");

            try { Thread.sleep(10); }
            catch (InterruptedException e) {}
            System.out.println("Thread 2: Waiting for lock 1...");

            synchronized (TaskA.class) {
                System.out.println("Thread 2: Holding lock 1 & 2...");
            }
        }
    }
}