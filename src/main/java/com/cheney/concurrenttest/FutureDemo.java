package com.cheney.concurrenttest;

import lombok.Data;

import java.util.concurrent.*;

/**
 * @author cheney
 * 日期 2024/6/18
 */
public class FutureDemo {
    public void main(String[] args) {
//        ExecutorService executor = Executors.newFixedThreadPool(1);
//        // 创建Result对象r
//        Result r = new Result();
//        r.setName(a);
//        // 提交任务
//        Future<Result> future = executor.submit(new Task(r), r);
//        Result fr = future.get();
//        // 下面等式成立
//        fr === r;
//        fr.getName() === a;
//        fr.getName() === x
//        class Task implements Runnable{
//            Result r;
//            //通过构造函数传入result
//            Task(Result r){
//                this.r = r;
//            }
//            void run() {
//                //可以操作result
//                a = r.getName();
//                r.setName(x);
//            }
//        }
    }

    public void futureTaskTest(){
        // 创建FutureTask
        FutureTask<Integer> futureTask = new FutureTask<>(()-> 1+2);
        // 创建线程池
        ExecutorService es =  Executors.newCachedThreadPool();
        // 提交FutureTask
        es.submit(futureTask);
        // 获取计算结果
        try {
            Integer result = futureTask.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

//// 创建FutureTask
//        FutureTask<Integer> futureTask = new FutureTask<>(()-> 1+2);
//// 创建并启动线程
//        Thread T1 = new Thread(futureTask);
//        T1.start();
//// 获取计算结果
//        Integer result = futureTask.get();
    }

    public void futureTaskTest1(){
//        // 创建任务T2的FutureTask
//        FutureTask<String> ft2 = new FutureTask<>(new T2Task());
//        // 创建任务T1的FutureTask
//        FutureTask<String> ft1 = new FutureTask<>(new T1Task(ft2));
//        // 线程T1执行任务ft1
//        Thread T1 = new Thread(ft1);
//        T1.start();
//        // 线程T2执行任务ft2
//        Thread T2 = new Thread(ft2);
//        T2.start();
//        // 等待线程T1执行结果
//        System.out.println(ft1.get());
//
//        // T1Task需要执行的任务：
//        // 洗水壶、烧开水、泡茶
//        class T1Task implements Callable<String>{
//            FutureTask<String> ft2;
//            // T1任务需要T2任务的FutureTask
//            T1Task(FutureTask<String> ft2){
//                this.ft2 = ft2;
//            }
//            @Override
//            String call() throws Exception {
//                System.out.println("T1:洗水壶...");
//                TimeUnit.SECONDS.sleep(1);
//
//                System.out.println("T1:烧开水...");
//                TimeUnit.SECONDS.sleep(15);
//                // 获取T2线程的茶叶
//                String tf = ft2.get();
//                System.out.println("T1:拿到茶叶:"+tf);
//
//                System.out.println("T1:泡茶...");
//                return "上茶:" + tf;
//            }
//        }
//        // T2Task需要执行的任务:
//        // 洗茶壶、洗茶杯、拿茶叶
//        class T2Task implements Callable<String> {
//            @Override
//            String call() throws Exception {
//                System.out.println("T2:洗茶壶...");
//                TimeUnit.SECONDS.sleep(1);
//
//                System.out.println("T2:洗茶杯...");
//                TimeUnit.SECONDS.sleep(2);
//
//                System.out.println("T2:拿茶叶...");
//                TimeUnit.SECONDS.sleep(1);
//                return "龙井";
//            }
//        }
    }
}
