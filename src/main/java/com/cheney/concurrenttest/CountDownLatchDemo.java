package com.cheney.concurrenttest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author cheney
 * 日期 2024/6/17
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);// 创建2个线程的线程池
        AtomicInteger atomicInteger = new AtomicInteger(10);
        while(atomicInteger.get() > 0){
            CountDownLatch latch = new CountDownLatch(2);// 计数器初始化为2
            executor.execute(()-> {
//                pos = getPOrders();// 查询未对账订单
                System.out.println("查询未对账订单");
                atomicInteger.decrementAndGet();
                latch.countDown();
            });
            executor.execute(()-> {
//                dos = getDOrders();// 查询派送单
                System.out.println("查询派送单");
                atomicInteger.decrementAndGet();
                latch.countDown();
            });
            latch.await();// 等待两个查询操作结束
//            diff = check(pos, dos);// 执行对账操作
//            save(diff);// 差异写入差异库
            System.out.println("执行对账操作,差异写入差异库");
        }
        executor.shutdown();
    }
}
