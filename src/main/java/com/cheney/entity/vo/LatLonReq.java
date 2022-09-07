package com.cheney.entity.vo;

import lombok.Data;

/**
 * 坐标点
 * @author linch
 */
@Data
public class LatLonReq {
    double latitude;
    double longitude;
    double radius;
    public LatLonReq(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    protected void test(){

    }
}
