package com.mashibing.springboot.other.threadPool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class T009_ThreadForkJoinPool {
    static int[] nums = new int[100];
    static final int MAX_NUM = 5;

    public T009_ThreadForkJoinPool() {
    }

    public static void main(String[] args) {
        ForkJoinPool fjp = new ForkJoinPool();
        AddTaskRet task = new AddTaskRet(0, nums.length);
        fjp.execute(task);
        Long value = (Long)task.join();
        System.out.println(value);
    }

    static class AddTaskRet extends RecursiveTask<Long> {
        private static final long serialVersionUID = 1L;
        int start;
        int end;

        AddTaskRet(int s, int e) {
            this.start = s;
            this.end = e;
        }

        protected Long compute() {
            if (this.end - this.start > 5) {
                int middle = this.start + (this.end - this.start) / 2;
                AddTaskRet subTask1 = new AddTaskRet(this.start, middle);
                AddTaskRet subTask2 = new AddTaskRet(middle, this.end);
                subTask1.fork();
                subTask2.fork();
                return (Long)subTask1.join() + (Long)subTask2.join();
            } else {
                long sum = 0L;

                for(int i = this.start; i < this.end; ++i) {
                    sum += (long)T009_ThreadForkJoinPool.nums[i];
                }

                return sum;
            }
        }
    }

    static class AddTask extends RecursiveAction {
        private static final long serialVersionUID = 1L;
        int start;
        int end;

        AddTask(int s, int e) {
            this.start = s;
            this.end = e;
        }

        protected void compute() {
            if (this.end - this.start <= 5) {
                long sum = 0L;

                for(int i = this.start; i < this.end; ++i) {
                    sum += (long)T009_ThreadForkJoinPool.nums[i];
                }

                System.out.println("from:" + this.start + " to:" + this.end + " = " + sum);
            } else {
                int middle = this.start + (this.end - this.start) / 2;
                AddTask subTask1 = new AddTask(this.start, middle);
                AddTask subTask2 = new AddTask(middle, this.end);
                subTask1.fork();
                subTask2.fork();
            }

        }
    }
}
