package com.cheney.concurrentdesign;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicLong;

public class ThreadLocalDemo {

    /**
     * 为每个线程分配一个唯一的线程 Id
     */
    static class ThreadId {
        static final AtomicLong nextId = new AtomicLong(0);
        //定义ThreadLocal变量
        static final ThreadLocal<Long> tl = ThreadLocal.withInitial(()->nextId.getAndIncrement());
        //此方法会为每个线程分配一个唯一的Id
        static long get(){
            return tl.get();
        }
    }

    /**
     *  SafeDateFormat
     */
    static class SafeDateFormat {
        //定义ThreadLocal变量
        static final ThreadLocal<DateFormat> tl = ThreadLocal.withInitial(()-> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        static DateFormat get(){
            return tl.get();
        }
    }

    public static void main(String[] args) {
        //不同线程执行下面代码//返回的df是不同的
        DateFormat df =  SafeDateFormat.get();
    }

}
