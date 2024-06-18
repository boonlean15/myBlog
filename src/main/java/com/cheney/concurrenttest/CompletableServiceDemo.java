package com.cheney.concurrenttest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author cheney
 * 日期 2024/6/18
 */
public class CompletableServiceDemo {
    public static void main(String[] args) {
        //实现询价系统
        Executor executor = Executors.newFixedThreadPool(3);
        CompletionService<Integer> service = new ExecutorCompletionService<>(executor);
        service.submit(() -> getPriceByS1());
        service.submit(() -> getPriceByS2());
        service.submit(() -> getPriceByS3());
        // 将询价结果异步保存到数据库
        for(int i =1;i<=3; i++){
            try {
                Integer r = service.take().get();
                executor.execute(()->save(r));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }

        }

        //实现Forking cluster
        Executor executorService = Executors.newFixedThreadPool(3);
        List<Future<Integer>> futureList = new ArrayList<>(3);
        CompletionService<Integer> cs = new ExecutorCompletionService<>(executorService);
        futureList.add(cs.submit(() -> getPriceByS1()));
        futureList.add(cs.submit(() -> getPriceByS2()));
        futureList.add(cs.submit(() -> getPriceByS3()));
        // 获取最快返回的任务执行结果
        Integer r = 0;
        try {
            // 只要有一个成功返回，则break
            for (int i = 0; i < 3; ++i) {
                try {
                    r = cs.take().get();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
                //简单地通过判空来检查是否成功返回
                if (r != null) {
                    break;
                }
            }
        } finally {
            //取消所有任务
            for(Future<Integer> f : futureList)
                f.cancel(true);
        }
    }

    static void save(Integer integer){
        System.out.println("save integer");
    }

    static Integer getPriceByS1(){
        return 1;
    }
    static Integer getPriceByS2(){
        return 2;
    }
    static Integer getPriceByS3(){
        return 3;
    }
}
