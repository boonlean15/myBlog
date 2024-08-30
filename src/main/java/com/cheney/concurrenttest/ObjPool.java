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
    final List<T> pool;
    final Semaphore sem;
    ObjPool(int size,T t) throws InstantiationException, IllegalAccessException {
        pool = new Vector<T>(){};
        for(int i=0; i<size; i++){
            Object o = t.getClass().newInstance();
            pool.add((T) o);
        }
        sem = new Semaphore(size);
    }
    // 利用对象池的对象，调用func
    R exec(Function<T,R> func) {
        T t = null;
        R r = null;
        try {
            sem.acquire();
            t = pool.remove(0);
            r = func.apply(t);
            System.out.println("r :" + r);
        } catch (InterruptedException e) {
            System.out.println("线程中断");
        }finally{
            pool.add(t);
            sem.release();
        }
        return r;
    }

    public static void main(String[] args) throws InterruptedException, InstantiationException, IllegalAccessException {
        // 创建对象池
        ObjPool<Allocator, String> pool = new ObjPool<>(10, new Allocator());
        // 通过对象池获取t，之后执行
        pool.exec(t -> {
            String simpleName = t.getClass().getSimpleName();
            return simpleName;
        });
        pool.exec(t -> {
            String typeName = t.getClass().getTypeName();
            return typeName;
        });
    }
}
