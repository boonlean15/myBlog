package com.cheney.executor;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class NamedThreadFactory implements ThreadFactory {

    private static final AtomicInteger THREAD_NUMBER = new AtomicInteger(1);
    private final ThreadGroup GROUP;
    private final boolean DAEMON;
    private String namePrefix = "addal-thread";

    public NamedThreadFactory() {
        this(null, false);
    }

    public NamedThreadFactory(String namePrefix) {
        this(namePrefix, false);
    }

    public NamedThreadFactory(String namePrefix, boolean daemon) {
        if (namePrefix != null) {
            this.namePrefix = namePrefix;
        }
        this.DAEMON = daemon;
        SecurityManager s = System.getSecurityManager();

        GROUP = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
    }

    @Override
    public Thread newThread(Runnable r) {
        return null;
    }
}
