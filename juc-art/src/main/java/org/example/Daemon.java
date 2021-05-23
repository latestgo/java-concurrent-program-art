package org.example;

import org.example.util.SleepUtils;

/**
 * Daemon 线程
 */
public class Daemon {

    public static void main(String[] args) {
        Thread daemonRunner = new Thread(new DaemonRunner(), "DaemonRunner");
        daemonRunner.setDaemon(true);
        daemonRunner.run();
    }

    static class DaemonRunner implements Runnable {
        @Override
        public void run() {
            try {
                SleepUtils.second(1);
            } finally {
                System.out.println("DaemonThread finally run.");
            }
        }
    }
}
