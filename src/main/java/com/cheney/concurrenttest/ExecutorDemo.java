package com.cheney.concurrenttest;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author cheney
 * 日期 2024/6/18
 */
public class ExecutorDemo {

    public static void main(String[] args) {
        /** 下面是使用示例 **/
        // 创建有界阻塞队列
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(2);
        // 创建线程池
        MyThreadPool pool = new MyThreadPool(10, workQueue);
        // 提交任务
        pool.execute(()->{
            System.out.println("hello");
        });
    }
}
