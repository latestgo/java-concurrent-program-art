package org.example;

import org.example.util.SleepUtils;

import java.util.concurrent.TimeUnit;

/**
 * 抛出异常后会清除中断标识位
 */
public class Interrupted {

    public static void main(String[] args) throws InterruptedException {
        // sleepRunner 不停地尝试睡眠
        Thread sleepRunner = new Thread(new SleepRunner(), "SleepRunner");
        sleepRunner.setDaemon(true);
        // busyThread 不停地运行
        Thread busyThread = new Thread(new BusyRunner(), "busyThread");
        busyThread.setDaemon(true);
        sleepRunner.start();
        busyThread.start();
        TimeUnit.SECONDS.sleep(5);
        sleepRunner.interrupt();
        busyThread.interrupt();
        System.out.println("SleepThread interrupted is " + sleepRunner.isInterrupted());
        System.out.println("BusyThread interrupted is " + busyThread.isInterrupted());
        SleepUtils.second(2);
    }

    static class SleepRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
                SleepUtils.second(10);
            }
        }
    }

    static class BusyRunner implements Runnable {
        @Override
        public void run() {
            while (true) {

            }
        }
    }
}
