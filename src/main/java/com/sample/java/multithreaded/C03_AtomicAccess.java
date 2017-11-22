package com.sample.java.multithreaded;

/**
 * {@link https://docs.oracle.com/javase/tutorial/essential/concurrency/atomic.html}
 *
 * An atomic action is one that effectively happens all at once. An atomic action cannot stop in the middle:
 * it either happens completely, or it doesn't happen at all.
 * No side effects of an atomic action are visible until the action is complete.
 *
 *
 * Reads and writes are atomic for reference variables and for most primitive variables (all types except long and double).
 * Reads and writes are atomic for all variables declared volatile (including long and double variables).
 *
 */
public class C03_AtomicAccess {
}


/**
 *  volatile long variable
 */
class CounterV5 {

    private volatile long c = 0;

    public void increment() {
        c++;
    }

    public long value() {
        return c;
    }
}
