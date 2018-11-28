package edu.concurrency.atomic;

import java.util.concurrent.TimeUnit;

public class Main {

    public static long foo;
    public static volatile long bar;
    public static final long A = 0xffffffffffffffffl;
    public static final long B = 0;
    public static int clock;

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Test());
            thread.start();
        }
        while (true) {
            long fooRead = foo;
            if (fooRead != A && fooRead != B) {
                System.err.println("foo incomplete write " + Long.toHexString(fooRead));
            }
            long barRead = bar;
            if (barRead != A && barRead != B) {
                System.err.println("bar incomplete write " + Long.toHexString(barRead));
            }
        }
    }
}

class Test implements Runnable {

    public void run() {
        while (true) {
            Main.foo = Main.clock % 2 == 0 ? Main.A : Main.B;
            Main.bar = Main.clock % 2 == 0 ? Main.A : Main.B;
            Main.clock++;
        }
    }
}


//https://stackoverflow.com/questions/3038203/is-there-any-point-in-using-a-volatile-long