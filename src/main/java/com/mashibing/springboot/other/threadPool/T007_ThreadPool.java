package com.mashibing.springboot.other.threadPool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class T007_ThreadPool {
    public T007_ThreadPool() {
    }

    public void systemThreadPool() throws Exception {
        System.out.println(" ----------------- ");
        ScheduledExecutorService service = Executors.newScheduledThreadPool(3);
        Future<String> future1 = service.submit(() -> {
            TimeUnit.MILLISECONDS.sleep(3000L);
            return "系统线程池执行完成";
        });
        Future<?> future2 = service.scheduleAtFixedRate(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(3000L);
            } catch (InterruptedException var1) {
                var1.printStackTrace();
            }

        }, 0L, 500L, TimeUnit.MILLISECONDS);
        Collection<Callable<String>> list = new ArrayList();
        list.add(new MyCallable("测试1"));
        list.add(new MyCallable("测试2"));
        List<Future<String>> futures = service.invokeAll(list);
        Iterator var6 = futures.iterator();

        while(var6.hasNext()) {
            Future<String> future = (Future)var6.next();
            System.out.println("invokeAll:" + (String)future.get());
        }

        String value = (String)future1.get();
        Object o = future2.get();
        System.out.println(value);
        System.out.println(o);
        service.shutdown();
    }

    public void customizeThreadPool() {
        System.out.println(" ----------------- ");
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 60000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue(5), Executors.defaultThreadFactory(), (r, executor1) -> {
        });
        executor.execute(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(3000L);
                System.out.println("自定义线程池执行完成");
            } catch (InterruptedException var1) {
                var1.printStackTrace();
            }

        });
        executor.shutdown();
        System.out.println("线程池是否被终止：" + executor.isTerminating());
        System.out.println("线程池线程任务是否执行完毕：" + executor.isTerminated());
    }

    public static void main(String[] args) throws Exception {
        T007_ThreadPool threadPool = new T007_ThreadPool();
        threadPool.customizeThreadPool();
        threadPool.systemThreadPool();
    }

    class MyCallable implements Callable<String> {
        public String value;

        public MyCallable(String value) {
            this.value = value;
        }

        public String call() throws Exception {
            return this.value;
        }
    }
}
