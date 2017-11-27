package com.sample.java.multithreaded;

import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

/**
 * {@link https://docs.oracle.com/javase/7/docs/api/index.html?java/util/concurrent/Semaphore.html}
 *
 * Semaphores are often used to restrict the number of threads than can access some (physical or logical) resource.
 * For example, here is a class that uses a semaphore to control access to a pool of items:
 */
public class C12_Semaphore {
}


class Pool {
    private static final int MAX_AVAILABLE = 100;
    private final Semaphore available = new Semaphore(MAX_AVAILABLE, true);

    public int getItem() throws InterruptedException {
        available.acquire();
        return getNextAvailableItem();
    }

    public void putItem(int x) {
        if (markAsUnused(x))
            available.release();
    }

    // Not a particularly efficient data structure; just for demo

    protected int[] items = IntStream.range(0, 999).toArray(); //... whatever kinds of items being managed
    protected boolean[] used = new boolean[MAX_AVAILABLE];

    protected synchronized int getNextAvailableItem() {
        for (int i = 0; i < MAX_AVAILABLE; ++i) {
            if (!used[i]) {
                used[i] = true;
                return items[i];
            }
        }
        return 0; // not reached
    }

    protected synchronized boolean markAsUnused(int item) {
        for (int i = 0; i < MAX_AVAILABLE; ++i) {
            if (item == items[i]) {
                if (used[i]) {
                    used[i] = false;
                    return true;
                } else
                    return false;
            }
        }
        return false;
    }

}
