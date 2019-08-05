package com.wlgdo.avatar.web;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @description: This is a TEST
 * @author: Ligang.Wang[wangligang@eglagame.com]
 * @create: 2019-07-26 12:10
 **/
public class ThreadFutrue {

    public static void main_(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 100; i++) {

            System.out.println(i);

            new Thread("I Am " + i) {
                @Override
                public void run() {
                    System.out.println(String.format("This Thread is 【%s】", this.getName()));
                }
            }.start();

            new Thread(new RunnerService("You Are" + i)).start();
        }
    }


    static class RunnerService implements Runnable {

        String runableName;

        RunnerService(String runableName) {
            this.runableName = runableName;
        }

        @Override
        public void run() {
            System.out.println(String.format("_[%s]_", runableName));
        }
    }



    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();

        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<String> results = exec.submit(new CallableFutrue("Helloworld-" + i));
            futures.add(results);
        }

        for (Future<String> ft : futures) {
            if (ft.isDone()) {
                try {
                    System.out.println("完成的线程：" + ft.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("未完成的线程：" + ft);
            }
        }
        exec.shutdown();

    }

    static class CallableFutrue implements Callable<String> {
        String result;

        CallableFutrue(String result) {
            this.result = result;
        }

        @Override
        public String call() throws Exception {
            System.out.println(String.format("This is [%s]", result.split("-")[1]));
            return this.result;
        }
    }
}


