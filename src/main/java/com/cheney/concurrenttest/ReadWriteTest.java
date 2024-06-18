package com.cheney.concurrenttest;

import lombok.extern.slf4j.Slf4j;

/**
 * @author cheney
 * 日期 2024/5/10
 */
@Slf4j
public class ReadWriteTest {

    static final int SHARED_SHIFT   = 16;
    static final int SHARED_UNIT    = (1 << SHARED_SHIFT);
    static final int MAX_COUNT      = (1 << SHARED_SHIFT) - 1;
    static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;

    static int exclusiveCount(int c) { return c & EXCLUSIVE_MASK; }

    static int sharedCount(int c)    { return c >>> SHARED_SHIFT; }

    public static void main(String[] args) {
//        int r = sharedCount(131072);
//        int j = exclusiveCount(131072);
//        log.info("r sharedCount == {}, j exclusiveCount == {}",r,j);
        test();
    }

    public static boolean test(){
        int c = 65536*2;
        int i = 0;
        for (;;) {
            log.info("i === {}",i);
            int nextc = c - SHARED_UNIT;
            i ++;
            if (i == 20)
                // Releasing the read lock has no effect on readers,
                // but it may allow waiting writers to proceed if
                // both read and write locks are now free.
                return nextc == 0;
        }
    }
}
