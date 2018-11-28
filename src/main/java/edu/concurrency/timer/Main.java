package edu.concurrency.timer;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Timer timer = new Timer();
        timer.schedule(new Task(), 4 * 1000);
        timer.schedule(new Task(), 4 * 1000);
        timer.schedule(new Task(), 4 * 1000);
        TimeUnit.SECONDS.sleep(10);
        //timer.cancel();
    }
}

class Task extends TimerTask {
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Task finished!");
    }
}