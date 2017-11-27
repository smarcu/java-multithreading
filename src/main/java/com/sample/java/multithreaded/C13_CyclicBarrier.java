package com.sample.java.multithreaded;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * {@link https://docs.oracle.com/javase/7/docs/api/index.html?java/util/concurrent/CyclicBarrier.html}
 *
 * A synchronization aid that allows a set of threads to all wait for each other to reach a common barrier point.
 * CyclicBarriers are useful in programs involving a fixed sized party of threads that must occasionally
 * wait for each other. The barrier is called cyclic because it can be re-used after the waiting threads are released.
 */
public class C13_CyclicBarrier {
}

class Solver {
    final int N;
    final float[][] data;
    final CyclicBarrier barrier;

    class Worker implements Runnable {
        int myRow;
        Worker(int row) { myRow = row; }
        public void run() {
            while (!done()) {

                processRow(myRow);

                try {
                    barrier.await();
                } catch (InterruptedException ex) {
                    return;
                } catch (BrokenBarrierException ex) {
                    return;
                }
            }
        }

        private boolean done() {
            //....
            return false;
        }

        private void processRow(int row) {
            //....
        }
    }

    public Solver(float[][] matrix) {
        data = matrix;
        N = matrix.length;
        barrier = new CyclicBarrier(N,
                new Runnable() {
                    public void run() {
                        mergeRows(/*...*/);
                    }
                });
        for (int i = 0; i < N; ++i)
            new Thread(new Worker(i)).start();

        waitUntilDone();
    }

    private boolean mergeRows() {
        //....
        return false;
    }

    private void waitUntilDone() {
        //....
    }

}