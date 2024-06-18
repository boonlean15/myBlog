package com.cheney.executor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class NameableExecutor extends ThreadPoolExecutor {

    private String name;

    NameableExecutor(String name, int size, BlockingQueue<Runnable> queue,
                     ThreadFactory factory) {
        super(size, size, Long.MAX_VALUE, TimeUnit.NANOSECONDS, queue, factory);
        this.name = name;
    }

}
