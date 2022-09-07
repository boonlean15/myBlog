package com.cheney.test;

import java.io.IOException;

/**
 * @author linch
 * @create 2021/12/13 15:22
 */
public class MyException extends IOException {

    public MyException(){}

    public MyException(String massage){
        super(massage);
    }
}
