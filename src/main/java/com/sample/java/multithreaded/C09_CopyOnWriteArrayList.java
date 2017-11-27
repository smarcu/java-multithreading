package com.sample.java.multithreaded;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * {@link https://docs.oracle.com/javase/8/docs/api/index.html?java/util/concurrent/CopyOnWriteArrayList.html}
 */
public class C09_CopyOnWriteArrayList {

    /**
     * A thread-safe variant of ArrayList in which all mutative operations (add, set, and so on) are implemented
     * by making a fresh copy of the underlying array.This is ordinarily too costly, but may be more efficient
     * than alternatives when traversal operations vastly outnumber mutations, and is useful when you cannot
     * or don't want to synchronize traversals
     */
    private CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();


    public void test() {

        list.add("test");
        list.addIfAbsent("test");

        list.removeIf(i -> i.equals("test"));

    }

}

