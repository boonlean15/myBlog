package com.cheney.concurrentcase;

import java.util.concurrent.TimeUnit;

public class StoreRateLimiter {

    //当前令牌桶中的令牌数量
    long storedPermits = 0;
    //令牌桶的容量
    long maxPermits = 3;
    //下一令牌产生时间
    long next = System.nanoTime();
    //发放令牌间隔：纳秒
    long interval = 1000_000_000;

    void acquire(){
        long now = System.nanoTime();
        long at = reserve(now);
        long waitTime = Math.max((at - now),0);
        if(waitTime > 0){
            try {
                TimeUnit.NANOSECONDS.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    synchronized long reserve(long now) {
        resync(now);
        //能够获取令牌的时间
        long at = next;
        //令牌桶中能提供的令牌
        long fb = Math.min(1, storedPermits);
        //令牌净需求：首先减掉令牌桶中的令牌
        long nr = 1 - fb;
        //重新计算下一令牌产生时间
        next = next + nr * interval;
        //重新计算令牌桶中的令牌
        storedPermits -= fb;
        return at;
    }

    //预约令牌
    void resync(long now) {
        if(now > next){
            //新产生的令牌数
            long newPermits = (now - next) / interval;
            storedPermits = Math.min(maxPermits,storedPermits + newPermits);
            next = now;
        }
    }

}
