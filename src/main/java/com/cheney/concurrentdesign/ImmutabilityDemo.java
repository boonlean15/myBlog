package com.cheney.concurrentdesign;

import lombok.extern.slf4j.Slf4j;

/**
 * 不可变模式Immutability
 * 类和属性final和所有方法只读
 * String Long Integer 等基础类型都是不可变类
 */
@Slf4j
public class ImmutabilityDemo {

    public static void main(String[] args) {
        Account account = new Account("test");
        String user = account.getUser();
        user = "fuck";
        log.info("account -- {}",account);
    }

    public static final class Account{
        private final String user;
        public Account(String user){
            this.user = user;
        }

        public String getUser(){
            return this.user;
        }
        public String toString(){
            return "user"+user;
        }
    }
}
