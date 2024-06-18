package com.cheney.concurrenttest;

import java.util.ArrayList;
import java.util.List;

/**
 * ture method to use lock
 *
 * @author cheney
 * 日期 2024/4/15
 */
public class Allocator {
    private List als = new ArrayList<>();
    synchronized boolean apply( Object from, Object to){
        if(als.contains(from) && als.contains(to)){
            return true;
        } else {
            als.add(from);
            als.add(to);
        }
        return true;
    }
    synchronized void free( Object from, Object to){
        als.remove(from);
        als.remove(to);
    }
    //改进方式
    public static Allocator getInstance(){
        return AllocatorSingle.allocator;
    }

    static class AllocatorSingle {
        final static Allocator allocator = new Allocator();
    }

    synchronized void applyNew(Object from ,Object to){
        while (als.contains(from) || als.contains(to)){
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        als.add(from);
        als.add(to);
    }

    synchronized void freeNew(Object from,Object to){
        als.remove(from);
        als.remove(to);
        this.notifyAll();
    }
}
