package com.cheney.concurrentmodel.stm;

/**
 * @author Cheney
 * @date 2024/10/15 14:29
 */
@FunctionalInterface
public interface TxnRunnable {
    void run(Txn txn);
}
