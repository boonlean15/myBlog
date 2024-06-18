package com.cheney.utils.concurrenttest;

/**
 * @author cheney
 * 日期 2024/6/17
 */
public class CyclicBarrierDemo {
    /*
    // 订单队列
    Vector<P> pos;
    // 派送单队列
    Vector<D> dos;
    // 执行回调的线程池
    Executor executor = Executors.newFixedThreadPool(1);
    final CyclicBarrier barrier =new CyclicBarrier(2, ()->{
        executor.execute(()->check());
      });
    void check(){
      P p = pos.remove(0);
      D d = dos.remove(0);
      diff = check(p, d);  // 执行对账操作
      save(diff);  // 差异写入差异库
    }
    void checkAll(){
      Thread T1 = new Thread(()->{
        while(存在未对账订单){  // 循环查询订单库
          pos.add(getPOrders());      // 查询订单库
          barrier.await();      // 等待
        }
      });
      T1.start();
      Thread T2 = new Thread(()->{
        while(存在未对账订单){  // 循环查询运单库
          dos.add(getDOrders());      // 查询运单库
          barrier.await();      // 等待
        }
      });
      T2.start();
    }
     */

}
