package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 基于CAS线程安全的计数器方法 safeCount 和一个非线程安全的计数器 count
 */
public class Counter {
    private AtomicInteger atomicI = new AtomicInteger();
    private int i = 0;

    public static void main(String[] args) throws InterruptedException {
        final Counter cas = new Counter();
        List<Thread> ts = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int j = 0; j < 100; j++) {
            Thread t = new Thread(() -> {
                for (int i = 0; i < 10000; i++) {
                    cas.safeCount();
                    cas.count();
                }
            });
            ts.add(t);
        }
        for (Thread t : ts) {
            t.start();
        }
        for (Thread t : ts) {
            t.join();
        }
        System.out.println(cas.i);
        System.out.println(cas.atomicI.get());
        System.out.println(System.currentTimeMillis() - start);
    }

    private void safeCount() {
        for (;;) {
            int i = atomicI.get();
            boolean suc =  atomicI.compareAndSet(i, ++i);
            if (suc) {
                break;
            }
        }
    }
    private void count() {
        i++;
    }
}
