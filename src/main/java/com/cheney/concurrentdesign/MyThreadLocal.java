package com.cheney.concurrentdesign;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * personal implement to ThreadLocal
 */
public class MyThreadLocal<T> {
    Map<Thread, T> locals = new ConcurrentHashMap<>();


    T get(){
        T t = locals.get(Thread.currentThread());
        return t;
    }

    void set(T t){
        locals.put(Thread.currentThread(),t);
    }

    /**
     * 手动清理让value实现回收
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        ThreadLocal tl = ThreadLocal.withInitial(() -> new AtomicInteger(1));
        es.execute(()->{
            //ThreadLocal增加变量
            tl.set(new Object());
            try {
                // 省略业务逻辑代码
            }finally {
                //手动清理ThreadLocal
                tl.remove();
            }
        });
    }
}

