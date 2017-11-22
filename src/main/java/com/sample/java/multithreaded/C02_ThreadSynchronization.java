package com.sample.java.multithreaded;

/**
 * {@link https://docs.oracle.com/javase/tutorial/essential/concurrency/syncmeth.html}
 * {@link https://docs.oracle.com/javase/tutorial/essential/concurrency/locksync.html}
 *
 * The Java programming language provides two basic synchronization idioms: synchronized methods and synchronized statements
 *
 */
public class C02_ThreadSynchronization {
}

/**
 * synchronized method
 */
class CounterV3 {

    private int c = 0;

    public synchronized void increment() {
        c++;
    }

    public synchronized int value() {
        return c;
    }
}

/**
 * synchronized statement
 */
class CounterV4 {

    private int c = 0;
    private Object lock = new Object();

    public void increment() {
        synchronized (lock) {
            c++;
        }
    }

    public int value() {
        synchronized (lock) {
            return c;
        }
    }
}
