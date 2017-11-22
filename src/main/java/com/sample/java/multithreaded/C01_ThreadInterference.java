package com.sample.java.multithreaded;


/**
 * {@link https://docs.oracle.com/javase/tutorial/essential/concurrency/interfere.html}
 *
 * Interference happens when two operations, running in different threads, but acting on the same data, interleave.
 * This means that the two operations consist of multiple steps, and the sequences of steps overlap.
 *
 * Operation ++ is not atomic (retrieve val, increment by 1, store value)
 *
 * Thread A: Retrieve c.
 * Thread B: Retrieve c.
 * Thread A: Increment retrieved value; result is 1.
 * Thread B: Decrement retrieved value; result is -1.
 * Thread A: Store result in c; c is now 1.
 * Thread B: Store result in c; c is now -1.
 */
public class C01_ThreadInterference {}


/**
 * non synchronized / non volatile - variable
 */
class Counter {

    private int c = 0;

    public void increment() {
        c++;
    }

    public int value() {
        return c;
    }
}

/**
 * using volatile
 */
class CounterV1 {

    private volatile int c = 0;

    public void increment() {
        c++;
    }

    public int value() {
        return c;
    }
}

