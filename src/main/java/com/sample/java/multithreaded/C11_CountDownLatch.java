package com.sample.java.multithreaded;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * {@link https://docs.oracle.com/javase/7/docs/api/index.html?java/util/concurrent/CountDownLatch.html}
 *
 * Latch - act as a gate, until the latch reaches the terminal state the gate is closed and no thread can pass.
 * Once in terminal state, it cannot change state again
 */
public class C11_CountDownLatch {
    public static final int THREADS = 10;
}


// Two latches used
// The first is a start signal that prevents any worker from proceeding until the driver is ready for them to proceed;
// The second is a completion signal that allows the driver to wait until all workers have completed.

class Driver { // ...
    void main() throws InterruptedException {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(C11_CountDownLatch.THREADS);

        for (int i = 0; i < C11_CountDownLatch.THREADS; ++i) // create and start threads
            new Thread(new Worker(startSignal, doneSignal)).start();

        //doSomethingElse();            // don't let run yet
        startSignal.countDown();      // let all threads proceed
        //doSomethingElse();
        doneSignal.await();           // wait for all to finish
    }
}

class Worker implements Runnable {
    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;
    Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }
    public void run() {
        try {
            startSignal.await();
            doWork();
            doneSignal.countDown();
        } catch (InterruptedException ex) {} // return;
    }

    void doWork() { /* ... */ }

}


// Another typical usage would be to divide a problem into N parts,
// describe each part with a Runnable that executes that portion and
// counts down on the latch, and queue all the Runnables to an Executor.
// When all sub-parts are complete, the coordinating thread will be able to pass through await.
// (When threads must repeatedly count down in this way, instead use a CyclicBarrier.)

class Driver2 { // ...
    void main() throws InterruptedException {
        CountDownLatch doneSignal = new CountDownLatch(C11_CountDownLatch.THREADS);
        Executor e = Executors.newScheduledThreadPool(C11_CountDownLatch.THREADS);

        for (int i = 0; i < C11_CountDownLatch.THREADS; ++i) // create and start threads
            e.execute(new WorkerRunnable(doneSignal, i));

        doneSignal.await();           // wait for all to finish
    }
}

class WorkerRunnable implements Runnable {
    private final CountDownLatch doneSignal;
    private final int i;
    WorkerRunnable(CountDownLatch doneSignal, int i) {
        this.doneSignal = doneSignal;
        this.i = i;
    }
    public void run() {
        try {
            doWork(i);
            doneSignal.countDown();
        } catch (InterruptedException ex) {} // return;
    }

    void doWork(int i) throws InterruptedException { /* ... */ }
}


