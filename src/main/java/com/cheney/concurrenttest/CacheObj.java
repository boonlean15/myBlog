package com.cheney.concurrenttest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock demo
 *
 * @author cheney
 * 日期 2024/5/10
 */
public class CacheObj<K, V> {
    final ReadWriteLock rwl = new ReentrantReadWriteLock();
    // 读锁
    final Lock r = rwl.readLock();
    // 写锁
    final Lock w = rwl.writeLock();
    final Map<K, V> m = new HashMap<>();

    // 读缓存
    V get(K key) {
        r.lock();
        try {
            return m.get(key);
        }
        finally {
            r.unlock();
        }
    }
    // 写缓存
    V put(K key, V v) {
        w.lock();
        try {
            return m.put(key, v);
        }
        finally {
            w.unlock();
        }
    }
    // 按需加载
    V getPlus(K key) {
        V v = null;
        //读缓存
        r.lock();         //①
        try {
            v = m.get(key); //②
        } finally{
            r.unlock();     //③
        }
        //缓存中存在，返回
        if(v != null) {   //④
            return v;
        }
        //缓存中不存在，查询数据库
        w.lock();         //⑤
        try {
            //再次验证
            //其他线程可能已经查询过数据库
            v = m.get(key); //⑥
            if(v == null){  //⑦
                //查询数据库
                //v=省略代码无数
                m.put(key, v);
            }
        } finally{
            w.unlock();
        }
        return v;
    }

}
