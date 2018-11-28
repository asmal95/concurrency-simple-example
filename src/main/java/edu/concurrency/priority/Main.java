package edu.concurrency.priority;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 9; i++) {
            Thread thread = new Thread(new Test());
            thread.setPriority(i+1);
            thread.start();
        }
        System.out.println("Main finished: " + Thread.currentThread().getName());
    }
}

class Test implements Runnable {
    public void run() {
        for (int i = 0; i < 30; i++) {

            String space = "";
            for (int j = 0; j < Thread.currentThread().getPriority(); j++) {
                space += "    ";
            }
            System.out.println(space + Thread.currentThread().getPriority());
        }
        System.out.println("Thread " + Thread.currentThread().getName() + " finished");
    }
}