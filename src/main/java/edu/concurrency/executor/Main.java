package edu.concurrency.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        /*Executor executor = Executors.newSingleThreadExecutor();

        executor.execute(new Task());*/

        /*ExecutorService executorService =  Executors.newFixedThreadPool(3);
        Future<String> submit = executorService.submit(new ResultedTask());
        while (!submit.isDone()) {
            System.out.println("wait...");
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(submit.get());*/


        //ScheduledExecutorService
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(); //TODO
        scheduledExecutorService.schedule(() -> {
            System.out.println("AAAAAAAAA!");
        }, 5, TimeUnit.SECONDS);
        //fixed delay - between calls
        //fixed rate - each time unit
        scheduledExecutorService.shutdown();
        System.out.println("Main finished: " + Thread.currentThread().getName());
    }
}

class Task implements Runnable {
    public void run() {
        System.out.println("my runnable finished: " + Thread.currentThread().getName());
    }
}

class ResultedTask implements Callable<String> {

    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(5);
        return "WELL DONE!";
    }
}