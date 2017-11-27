package com.sample.java.multithreaded;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * {@link https://docs.oracle.com/javase/tutorial/essential/concurrency/executors.html}
 *
 * Task - logical unit of work
 * Executor - provides a way of decoupling task submission from the mechanics of how each task will be run,
 * including details of thread use, scheduling, etc.
 *
 * An Executor is normally used instead of explicitly creating threads.
 *
 */
public class C14_Executors {

    // An object that executes submitted Runnable tasks. This interface provides a way of decoupling task
    // submission from the mechanics of how each task will be run, including details of thread use, scheduling, etc.
    private Executor executor = Executors.newFixedThreadPool(4);

    // An {@link Executor} that provides methods to manage termination and
    // methods that can produce a Future for tracking progress of one or more asynchronous tasks.
    private ExecutorService executorService = Executors.newFixedThreadPool(4);

    // An ExecutorService that can schedule commands to run after a given delay, or to execute periodically.
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);


    public void executorExample() throws InterruptedException {
        for (int i=0; i<4; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Executor example: Thread " + Thread.currentThread() + " run");
                }
            });
        }
    }

    public void executorServiceExample() throws InterruptedException, ExecutionException {
        List<Future<String>> futures = new ArrayList<>();
        for (int i=0; i<4; i++) {
            futures.add( executorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    System.out.println("ExecutorService example: Thread " + Thread.currentThread() + " run");
                    return Thread.currentThread().getName();
                }
            }));
        }
        boolean finished = executorService.awaitTermination(1000, TimeUnit.MILLISECONDS);
        futures.stream().forEach(f -> {
                try {
                    System.out.println("ExecutorService example: "+f.get());
                } catch (Exception e) {
                    System.out.println(e);
                }
            });
    }

    public void scheduledExecutorServiceExample() throws InterruptedException {
        List<Future<String>> futures = new ArrayList<>();
        for (int i=0; i<4; i++) {
            final int v = i;
            futures.add( scheduledExecutorService.schedule(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    System.out.println("ScheduledExecutorService example: Thread " + Thread.currentThread() + " run "+new Date());
                    return Thread.currentThread().getName() + " i:" + v;
                }
            }, 1000 * i, TimeUnit.MILLISECONDS));
        }
        boolean finished = scheduledExecutorService.awaitTermination(1000, TimeUnit.MILLISECONDS);
        futures.stream().forEach(f -> {
            try {
                System.out.println("ScheduledExecutorService example: "+f.get());
            } catch (Exception e) {
                System.out.println(e);
            }
        });
    }

    public static void main(String s[]) throws Exception {
        C14_Executors executors = new C14_Executors();
        executors.executorExample();
        executors.executorServiceExample();
        executors.scheduledExecutorServiceExample();
    }

}

