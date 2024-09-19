package com.cheney.concurrentcase;

import java.util.concurrent.TimeUnit;

public class NoStoreRateLimiter {
    //下一令牌产生时间
    long next = System.nanoTime();
    //发放令牌时间间隔:纳秒
    long interval = 1000_000_000;

    //申请令牌
    void acquire(){
        //申请令牌时的时间
        long now = System.nanoTime();
        //预占令牌
        long at = reserve(now);
        long waitTime = Math.max((at - now),0L);
        //按照条件等待
        if(waitTime > 0l){
            try {
                TimeUnit.NANOSECONDS.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //预占令牌 返回能够获取令牌的时间
    synchronized long reserve(long now) {
        //请求时间在下一令牌产生时间之后
        //重新计算下一令牌产生时间
        if(now > next){
            next = now;//将下一令牌产生时间重置为当前时间
        }
        long at = next;//能够获取令牌的时间
        next += interval;//设置下一令牌产生时间
        return Math.max(at, 0L);//返回获取令牌的时间
    }
}
