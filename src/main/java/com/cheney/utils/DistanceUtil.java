package com.cheney.utils;


import com.cheney.entity.vo.LatLonReq;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalCoordinates;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linch
 */
public class DistanceUtil {
    //圆周率
    private static final double PI = 3.14159265358979323;
    //地球的半径
    private static final double R = 6371229;

    public static double getDistance(double longitudeFrom, double latitudeFrom, double longitudeTo, double latitudeTo) {
        GlobalCoordinates source = new GlobalCoordinates(latitudeFrom, longitudeFrom);
        GlobalCoordinates target = new GlobalCoordinates(latitudeTo, longitudeTo);

        return new GeodeticCalculator().calculateGeodeticCurve(Ellipsoid.Sphere, source, target).getEllipsoidalDistance();
    }

    public static List<LatLonReq> calculateLatLon(LatLonReq latLon, double distance){
        List<LatLonReq> latLonReq = new ArrayList<>();
        GlobalCoordinates start = new GlobalCoordinates(latLon.getLatitude(), latLon.getLongitude());
        GeodeticCalculator geoCalc = new GeodeticCalculator();
        double longitude = latLon.getLongitude();
        double latitude = latLon.getLatitude();
        GlobalCoordinates globalCoordinate1 = geoCalc.calculateEndingGlobalCoordinates(Ellipsoid.Sphere, start, latitude, distance);
        GlobalCoordinates globalCoordinate2 = geoCalc.calculateEndingGlobalCoordinates(Ellipsoid.Sphere, start, longitude, distance);
        latLonReq.add(new LatLonReq(globalCoordinate1.getLatitude(), globalCoordinate1.getLongitude()));
        latLonReq.add(new LatLonReq(globalCoordinate2.getLatitude(), globalCoordinate2.getLongitude()));
        return latLonReq;
    }

}
