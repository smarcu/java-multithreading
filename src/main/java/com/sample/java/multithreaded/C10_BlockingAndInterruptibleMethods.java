package com.sample.java.multithreaded;

/**
 * {@link https://docs.oracle.com/javase/tutorial/essential/concurrency/interrupt.html}
 *
 *
 * The distinction between a blocking operation and an ordinary
 * operation that can take a long time to finish is that a blocked
 * thread must wait for an event that is beyond its control before it
 * can proceed (i/o completes, lock becomes available, external computation finishes)
 * state back to RUNNABLE and is eligible for scheduling
 */
public class C10_BlockingAndInterruptibleMethods {



    public void blockingMethodUsage() throws Exception {

        // This method blocks until input data is available, the end of the stream is detected, or an exception is thrown
        System.in.read();

    }

    public void interruptibleMethodUsage() throws InterruptedException {

        try {

            longRunningMethod();

        } catch (InterruptedException e) {
            // the interrupted flag is reset
            // handle the exception, do not ignore it
            // Options:
            // 1. Restore the interrupt  (not always possible to propagate the exception)
            Thread.interrupted();
            // 2. Propagate the InterruptedException
            throw e;
        }


    }

    public void longRunningMethod() throws InterruptedException {

        for (int i = 0; i < 999999999; i++) {

            //heavyComputation ....

            // Allow others to interrupt the long running method
            if (Thread.interrupted()) {  // clears the interrupt flag
                // We've been interrupted: no more crunching.
                throw new InterruptedException();
            }
        }

    }

}

