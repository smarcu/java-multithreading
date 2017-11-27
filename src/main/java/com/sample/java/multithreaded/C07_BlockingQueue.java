package com.sample.java.multithreaded;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * {@link https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingQueue.html}
 */
public class C07_BlockingQueue {
}

class Producer implements Runnable {
    private final BlockingQueue queue;

    Producer(BlockingQueue q) {
        queue = q;
    }

    public void run() {
        try {
            while (true) {
                queue.put(produce());
            }
        } catch (InterruptedException ex) { /*... handle ... */ }
    }

    Object produce() {
        System.out.println("produce...");
        return new Date();
    }
}

class Consumer implements Runnable {
    private final BlockingQueue queue;

    Consumer(BlockingQueue q) {
        queue = q;
    }

    public void run() {
        try {
            while (true) {
                consume(queue.take());
            }
        } catch (InterruptedException ex) { /*... handle ... */ }
    }

    void consume(Object x) {
        System.out.println("cosume: " + x);
    }
}

class Setup {
    public static void main(String s[]) {
        BlockingQueue q = new ArrayBlockingQueue(2);
        Producer p = new Producer(q);
        Consumer c1 = new Consumer(q);
        Consumer c2 = new Consumer(q);
        new Thread(p).start();
        new Thread(c1).start();
        new Thread(c2).start();
    }
}