package com.cheney.concurrentmodel.stm;

/**
 * 带版本号的对象引用 不变性Immutability模式
 * @author Cheney
 * @date 2024/10/15 11:32
 */
public final class VersionedRef<T> {

    final T value;
    final long version;

    public VersionedRef(T value, long version){
        this.value = value;
        this.version = version;
    }

}
