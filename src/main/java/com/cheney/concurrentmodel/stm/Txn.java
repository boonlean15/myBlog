package com.cheney.concurrentmodel.stm;

/**
 * 事务接口
 * @author Cheney
 * @date 2024/10/15 11:40
 */
public interface Txn {
    <T> T get(TxnRef<T> ref);
    <T> void set(TxnRef<T> ref, T value);
}
