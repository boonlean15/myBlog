package com.cheney.utils.concurrenttest;

/**
 * @author cheney
 * 日期 2024/4/15
 */
public class Account {
    private Allocator actr;
    private int balance;
    void transfer(Account target, int amt){
        getActr();
        // 一次性申请转出账户和转入账户，直到成功
        while(!actr.apply(this, target));
        try{
            // 锁定转出账户
            synchronized(this){
                // 锁定转入账户
                synchronized(target){
                    if (this.balance > amt){
                        this.balance -= amt;
                        target.balance += amt;
                    }
                }
            }
        } finally {
            actr.free(this, target);
        }
    }

    private synchronized void getActr(){
        if(actr == null){
            actr = new Allocator();
        }
    }

    public static void main(String[] args) {
        int i = 30;
        int j = 200;
        int z = i * j;
        int in = 15500;
        int out1 = 7400;
        int out2 = 5550;
        int out3 = 500;
        int life = 1000 + 200 + 200 + 1000;
        int save = in + z - out1 - out2 - out3 - life;
        System.out.println("save " + save);
        int month = 28;
        int total = save * month;
        System.out.println("total " + total);
    }

}
