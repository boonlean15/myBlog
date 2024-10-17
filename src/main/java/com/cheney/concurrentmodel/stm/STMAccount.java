package com.cheney.concurrentmodel.stm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Cheney
 * @date 2024/10/15 14:32
 */
class STMAccount {
    //余额
    private TxnRef<Integer> balance;
    //构造方法
    public STMAccount(int balance) {
        this.balance = new TxnRef<Integer>(balance);
    }
    //转账操作
    public void transfer(STMAccount target, int amt){
        STM.atomic((txn)->{
            Integer from = balance.getValue(txn);
            balance.setValue(from-amt, txn);
            Integer to = target.balance.getValue(txn);
            target.balance.setValue(to+amt, txn);
        });
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(50);
        STMAccount target = new STMAccount(100);
        UnsafeAccount unsafeTarget = new UnsafeAccount(100);
        for(int i = 0; i < 10000; i++){
            es.execute(() -> {
                STMAccount account = new STMAccount(100);
                account.transfer(target, 100);
                UnsafeAccount unsafeAccount = new UnsafeAccount(100);
                unsafeAccount.transfer(unsafeTarget,100);
            });
        }
        es.shutdown();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("STM: " + target.balance.curRef.value);
        System.out.println("unsafe: " + unsafeTarget.balance);
    }
}
