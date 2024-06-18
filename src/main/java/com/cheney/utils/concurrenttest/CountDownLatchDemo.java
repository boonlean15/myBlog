package com.cheney.utils.concurrenttest;

/**
 * @author cheney
 * 日期 2024/6/17
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        /*
        Executor executor = Executors.newFixedThreadPool(2);// 创建2个线程的线程池
        while(存在未对账订单){
            CountDownLatch latch = new CountDownLatch(2);// 计数器初始化为2
            executor.execute(()-> {
                pos = getPOrders();// 查询未对账订单
                latch.countDown();
            });
            executor.execute(()-> {
                dos = getDOrders();// 查询派送单
                latch.countDown();
            });
            latch.await();// 等待两个查询操作结束
            diff = check(pos, dos);// 执行对账操作
            save(diff);// 差异写入差异库
        }
         */
    }
}
