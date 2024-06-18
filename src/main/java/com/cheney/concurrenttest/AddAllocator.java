package com.cheney.concurrenttest;

import java.util.Collections;
import java.util.List;

/**
 * ture method to use lock
 *
 * @author cheney
 * 日期 2024/6/13
 */
public class AddAllocator<T> {

    private List<T> list;

    public synchronized void apply(T t){
        while (list.contains(t)){
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        list.add(t);
        Collections.synchronizedList(list);
    }

    public synchronized void free(T t){
        list.remove(t);
        this.notifyAll();
    }

    public static AddAllocator getInstance(){
        return AddAllocatorSingle.addAllocator;
    }
    static class AddAllocatorSingle{
        static final AddAllocator addAllocator = new AddAllocator();
    }
}
