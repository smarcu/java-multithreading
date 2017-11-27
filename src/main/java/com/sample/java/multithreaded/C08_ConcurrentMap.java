package com.sample.java.multithreaded;

import java.util.concurrent.ConcurrentHashMap;

/**
 * {@link https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentMap.html}
 * {@link https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentHashMap.html}
 */
public class C08_ConcurrentMap {

    private ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap();

    /**
     * using putIfAbsent, worst case scenario multiple invocations to same generateId
     */
    public String computeUniqueId(String item) {
        String id = concurrentHashMap.get(item);
        if (id == null) {
            String existentId = concurrentHashMap.putIfAbsent(item, generateId(item));
            // existentId null - this thread inserted the value

            id = concurrentHashMap.get(item);
        }
        return id;
    }

    /**
     * using computeIfAbsent, only one invocation of generateId
     */
    public String computeUniqueId_v2(String item) {
        String id = concurrentHashMap.get(item);
        if (id == null) {
            concurrentHashMap.computeIfAbsent( item, i ->generateId(i) );
            id = concurrentHashMap.get(item);
        }
        return id;
    }

    /**
     * How things looked without ConcurrentMap, using put non atomic operation
     */
    public String computeUniqueId_v3(String item) {
        String id = concurrentHashMap.get(item);
        if (id == null) {
            synchronized (this) {
                id = concurrentHashMap.get(item);
                if (id == null) {
                    id = generateId(item);
                    concurrentHashMap.put(item, id);
                }
            }
            id = concurrentHashMap.get(item);
        }
        return id;
    }

    private String generateId(String item) {
        return "ID_"+item;
    }
}