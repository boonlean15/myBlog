package com.cheney.utils;

import com.cheney.entity.vo.LatLonReq;

/**
 * @author linch
 * @create 2021/12/8 16:40
 */
public class Test implements Cloneable{

    @Override
    public Test clone() throws CloneNotSupportedException {
        return (Test) super.clone();
    }
}
