package com.cheney.concurrenttest;

import java.util.concurrent.locks.StampedLock;

/**
 * @author cheney
 * 日期 2024/5/20
 */
public class StampedLockDemo {
/*    乐观读升级悲观读锁最佳实践
    private int x,y;
    private final StampedLock stampedLock = new StampedLock();

    int distanceFromOrigin() {
        long stamp = stampedLock.tryOptimisticRead();
        int curX = x;int curY = y;
        //如果有写操作，validate返回false
        if(!stampedLock.validate(stamp)){
            //如果存在写操作，升级为悲观读锁
            stamp = stampedLock.readLock();
            try {
                curX = x; curY = y;
            } finally {
                //释放悲观读锁
                stampedLock.unlockRead(stamp);
            }
        }
        return (int) Math.sqrt( curX * curX + curY * curY);
    }*/

    private double x, y;
    final StampedLock sl = new StampedLock();
    // 存在问题的方法
    void moveIfAtOrigin(double newX, double newY) {
        long stamp = sl.readLock();
        try {
            while (x == 0.0 && y == 0.0) {
                long ws = sl.tryConvertToWriteLock(stamp);
                if (ws != 0L) {
                    x = newX;
                    stamp = ws;
                    y = newY;//正确的应该这里加上stamp = ws; ws不等于0L就是升级了写锁，那么最后要unlock的就是ws
                    break;
                } else {
                    sl.unlockRead(stamp);
                    stamp = sl.writeLock();
                }
            }
        } finally {
            sl.unlock(stamp);
        }
    }

    public static void main(String[] args) {
        final StampedLock sl = new StampedLock();
        // 获取/释放悲观读锁示意代码
        long stamp = sl.readLock();
        try {
            //省略业务相关代码
        } finally {
            sl.unlockRead(stamp);
        }
        // 获取/释放写锁示意代码
        stamp = sl.writeLock();
        try {
            //省略业务相关代码
        } finally {
            sl.unlockWrite(stamp);
        }
    }
}
