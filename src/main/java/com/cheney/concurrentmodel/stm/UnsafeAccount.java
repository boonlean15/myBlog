package com.cheney.concurrentmodel.stm;

/**
 * @author Cheney
 * @date 2024/10/15 17:53
 */
public class UnsafeAccount {

    //余额
    public Integer balance;
    //构造方法
    public UnsafeAccount(int balance) {
        this.balance = balance;
    }
    //转账操作
    public void transfer(UnsafeAccount target, int amt){
        this.balance -= amt;
        target.balance += amt;
    }

}
