package com.mashibing.springboot.other.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class T006_ThreadBlockingDelayQueue {
    public T006_ThreadBlockingDelayQueue() {
    }

    public static void main(String[] args) throws Exception {
        BlockingQueue<DelayTask> tasks = new DelayQueue();
        long now = System.currentTimeMillis();
        tasks.put(new DelayTask("t1", now + 10000L));
        tasks.put(new DelayTask("t2", now + 500L));
        tasks.put(new DelayTask("t3", now + 600L));
        tasks.put(new DelayTask("t4", now + 200L));

        for(int i = 0; i < 5; ++i) {
            DelayTask take = (DelayTask)tasks.take();
            System.out.println(take.name);
        }

    }

    static class DelayTask implements Delayed {
        String name;
        long runningTime;

        DelayTask(String name, long rt) {
            this.name = name;
            this.runningTime = rt;
        }

        public long getDelay(TimeUnit unit) {
            return unit.convert(this.runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
                return -1;
            } else {
                return this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS) ? 1 : 0;
            }
        }
    }
}
