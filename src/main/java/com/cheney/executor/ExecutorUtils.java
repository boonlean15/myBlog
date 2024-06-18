package com.cheney.executor;

import java.util.concurrent.LinkedTransferQueue;

public class ExecutorUtils {

    public static final NameableExecutor create(String name, int size) {
        return create(name, size, true);
    }

    private static final NameableExecutor create(String name, int size,
                                                 boolean isDaemon) {
        NamedThreadFactory factory = new NamedThreadFactory(name, isDaemon);
        return new NameableExecutor(name, size, new LinkedTransferQueue<Runnable>(),
                factory);
    }

    public static void main(String[] args) {
        ExecutorUtils.create("my-executor", 8);
    }
}
