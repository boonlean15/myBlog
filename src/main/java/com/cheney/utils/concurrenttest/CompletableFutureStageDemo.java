package com.cheney.utils.concurrenttest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static com.cheney.utils.concurrenttest.CompletableFutureDemo.sleep;

/**
 * @author cheney
 * 日期 2024/6/18
 */
public class CompletableFutureStageDemo {

    public static void main(String[] args) {
        //串行
        CompletableFuture<String> f0 =
                CompletableFuture.supplyAsync(
                                () -> "Hello World")      //①
                        .thenApply(s -> s + " QQ")  //②
                        .thenApply(String::toUpperCase);//③
        System.out.println(f0.join());
        //OR聚合
        CompletableFuture<String> f1 =
                CompletableFuture.supplyAsync(()->{
                    int t = 5;
                    sleep(t, TimeUnit.SECONDS);
                    return String.valueOf(t);
                });
        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(()->{
                    int t = 10;
                    sleep(t, TimeUnit.SECONDS);
                    return String.valueOf(t);
                });
        CompletableFuture<String> f3 =
                f1.applyToEither(f2,s -> s);
        System.out.println(f3.join());
        //异常处理
        CompletableFuture<Integer>
                f00 = CompletableFuture
                .supplyAsync(()->(7/0))
                .thenApply(r->r*10)
                .exceptionally(e->0);
        System.out.println(f00.join());

    }
}
