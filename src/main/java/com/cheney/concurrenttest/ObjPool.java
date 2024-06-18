package com.cheney.concurrenttest;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * semaphore use demo
 *
 * @author cheney
 * 日期 2024/5/9
 */
public class ObjPool<T,R> {
    final List<Object> pool;
    final Semaphore sem;
    ObjPool(int size,T t) throws InstantiationException, IllegalAccessException {
        pool = new Vector<Object>(){};
        for(int i=0; i<size; i++){
            Object o = t.getClass().newInstance();
            pool.add(o);
        }
        sem = new Semaphore(size);
    }
    // 利用对象池的对象，调用func
    R exec(Function<Object,R> func) {
        Object t = null;
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            System.out.println("线程中断");
            sem.release();
        }
        try{
            t = pool.remove(0);
            return func.apply(t);
        }finally{
            pool.add(t);
            sem.release();
        }
    }

    public static void main(String[] args) throws InterruptedException, InstantiationException, IllegalAccessException {
        // 创建对象池
        ObjPool<Allocator, String> pool = new ObjPool<>(10, new Allocator());
        // 通过对象池获取t，之后执行
        pool.exec(t -> {
            System.out.println(t);
            return t.toString();
        });
    }
}
