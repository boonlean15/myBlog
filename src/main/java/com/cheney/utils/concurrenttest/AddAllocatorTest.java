package com.cheney.utils.concurrenttest;

/**
 * @author cheney
 * 日期 2024/6/13
 */
public class AddAllocatorTest {

    public static void main(String[] args) {
        AddAllocator addAllocator = AddAllocator.getInstance();
        Account account = new Account();
        addAllocator.apply(account);
        //执行add操作
        System.out.println("执行add操作");
        addAllocator.free(account);
    }
}
