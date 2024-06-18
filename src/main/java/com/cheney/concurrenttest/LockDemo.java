package com.cheney.concurrenttest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author cheney
 * 日期 2024/6/18
 */
public class LockDemo {

    private final Lock rtl = new ReentrantLock();
    int value;
    public int get() {
        // 获取锁
        rtl.lock();         //②
        try {
            return value;
        } finally {
            // 保证锁能释放
            rtl.unlock();
        }
    }
    public void addOne() {
        // 获取锁
        rtl.lock();
        try {
            value = 1 + get(); //①
        } finally {
            // 保证锁能释放
            rtl.unlock();
        }
    }

}
