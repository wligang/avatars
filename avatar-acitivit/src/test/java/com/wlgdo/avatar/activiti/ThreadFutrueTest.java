package com.wlgdo.avatar.activiti;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @description: This is a TEST
 * @author: Ligang.Wang[wangligang@eglagame.com]
 * @create: 2019-07-26 12:10
 **/
public class ThreadFutrueTest {

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
        List<Future<String>> futureTaskList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            FutureTask futureTask = new FutureTask(new CallableFutrue(String.format(" \t [H-%s]", i)));
            futureTaskList.add(futureTask);
            Thread worker = new Thread(futureTask, "慢速累加器线程" + i);
            worker.start();
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (Future<String> futureTask : futureTaskList) {
            try {
                stringBuffer.append(futureTask.get()); // get() 方法会阻塞直到获得结果
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println(String.format("最后的结果是：[%s]", stringBuffer));
    }

    /**
     * 有返回值的多线程任务
     */
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


