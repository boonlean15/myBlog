import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson.JSON;
import com.cheney.MyBlogApplication;
import com.cheney.entity.vo.LatLonReq;
import com.cheney.test.easyexcel.entity.CadHelpExcel;
import com.cheney.test.easyexcel.entity.OutPutExcel;
import com.cheney.test.easyexcel.entity.ResultExcel;
import com.cheney.test.easyexcel.listener.ResultExcelListener;
import com.cheney.utils.DistanceUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Console;
import java.io.File;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RunWith(SpringRunner.class)
//@SpringBootTest(classes = MyBlogApplication.class)
public class MyblogTest {

    //圆周率
    private static final double PI = 3.14159265358979323;
    //地球的半径
    private static final double R = 6371229;


    @Test
    public void test1(){
        System.out.println("经纬度距离计算结果：" + DistanceUtil.getDistance(104.32939, 26.30551, 104.228444, 24.777787) + "米");

        List<LatLonReq> latLonReqs = DistanceUtil.calculateLatLon(new LatLonReq(26.30551, 104.32939), 170176);

        latLonReqs.stream().forEach(latLon -> {
            System.out.println("-----------坐标-----------" + latLon.getLatitude() + "----------" + latLon.getLongitude());
        });

        double latitude = 26.30551;
        double longitude = 104.32939;
        //TODO:在经线上，纬度每差1度,实地距离大约为111千米  111198.9234485458
        double oneLatitudeDistance = PI * R / 180;
        System.out.println("在经线上，纬度每差1度,实地距离 ------- " + oneLatitudeDistance );
        //TODO:在纬线上，经度每差1度,实际距离为111×cosθ千米。（其中θ表示该纬线的纬度.在不同纬线上,经度每差1度的实际距离是不相等的）。
        // Math.cos(0) = 111198.9234485458 cos(-26.30551)=43100.79661213256 cos(26.30551)=43100.79661213256
        double oneLongitudeDistance = oneLatitudeDistance * Math.cos(latitude);
        System.out.println("在纬线上，经度每差1度,实际距离为111×cosθ千米 ------- " + oneLongitudeDistance );

        double latitudeCal = 500 / oneLatitudeDistance;
        System.out.println("500米距离是多少度 latitudeCal - " + latitudeCal);
        double longitudeCal = 500 / oneLongitudeDistance;
        System.out.println("500米距离是多少度 longitudeCal - " + longitudeCal);

        double newLat = latitude + latitudeCal;
        System.out.println("500米计算后新的纬度是 - " + newLat);
        System.out.println("500米计算后新的纬度坐标是 - " + newLat + "---" + longitude);
        double newLon = longitude + longitudeCal;
        System.out.println("500米计算后新的经度是 - " + newLon);
        System.out.println("500米计算后新的经度坐标是 - " + newLon + "---" + latitude);


        System.out.println("新纬度距离计算结果 ------- 需要作为对比：" + DistanceUtil.getDistance(
                104.32939, 26.30551, 104.32939, 26.310006) + "米");
        System.out.println("新经度距离计算结果 ------- 需要作为对比：" + DistanceUtil.getDistance(
                104.32939, 26.30551, 105.32939, 26.30551) + "米");

    }

    @Test
    public void test2(){
        System.out.println("纬度相同----------------------------");
        System.out.println("纬度26.30551 104-105经度差等于：" + DistanceUtil.getDistance(
                104.32939, 0.0, 105.32939, 0.0) + "米");
        System.out.println("纬度26.30551 104-103经度差等于：" + DistanceUtil.getDistance(
                104.32939, -26.30551, 103.32939, -26.30551) + "米");
        System.out.println("纬度24.777787 104-105经度差等于：" + DistanceUtil.getDistance(
                104.228444, 24.777787, 105.228444, 24.777787) + "米");
        System.out.println("纬度24.777787 104-103经度差等于：" + DistanceUtil.getDistance(
                104.228444, 24.777787, 103.228444, 24.777787) + "米");
//99679.70611723118
        double re = 500 / DistanceUtil.getDistance(
                104.32939, 26.30551, 105.32939, 26.30551);
        double count = 104.32939 + re;
        double count1 = 104.32939 - re;
        System.out.println("----------count----" + count + "-----------count1 ------ " + count1);

        System.out.println("经度相同----------------------------");
        System.out.println("经度104.32939 26-27纬度度差等于：" + DistanceUtil.getDistance(
                104.32939, 26.30551, 104.32939, 27.30551) + "米");
        System.out.println("经度104.32939 26-25纬度度差等于：" + DistanceUtil.getDistance(
                104.32939, 26.30551, 104.32939, 25.30551) + "米");
        System.out.println("经度103.228444 26-27纬度度差等于：" + DistanceUtil.getDistance(
                103.228444, 26.30551, 103.228444, 27.30551) + "米");
        System.out.println("经度103.228444 26-25纬度度差等于：" + DistanceUtil.getDistance(
                103.228444, 26.30551, 103.228444, 25.30551) + "米");

    }
    
    @Test
    public void test3(){
        String age = null + "-" + null + "-";
        System.out.println(age);
    }


    @Test
    public void testOptional(){
        UserTest userTest = new UserTest();
        userTest.setName("liu li han");
        userTest.setEmail("liu li han email");
        Optional.ofNullable(userTest).ifPresent(u -> {
            log.info(u.getName());
        });
        // ofNullable连续调用,只有最后一个条件生效,即判断的最后一个判断
        // isPresent 返回的boolean ifPresent 可以接收lambda表达式,如果当前判断为true则执行表达式内容,false不执行
        Optional.ofNullable(userTest).ofNullable(userTest.getName()).ifPresent(name -> log.info("连环判断name - " + name));
        Optional.ofNullable(userTest.getName()).ofNullable(userTest.getAge()).ifPresent(age -> log.info("连环判断age - " + age));
        Optional.ofNullable(userTest.getName()).ofNullable(userTest.getAge()).ofNullable(userTest.getEmail()).ifPresent(email -> log.info("连环判断Email - " + email));


        String logAge = Optional.ofNullable(userTest.getAge()).orElseGet(() -> new String("hhhhhelel"));
        log.info("-----logAge----------" + logAge);
        double radians = Optional.ofNullable(userTest.getTestBig()).orElseGet(() -> new BigDecimal(30)).doubleValue();
        log.info("---- radians ---- " + radians);

    }

    public static double getDistance(LatLonReq latLonReq1, LatLonReq latLonReq2) {
        GlobalCoordinates source = new GlobalCoordinates(latLonReq1.getLatitude(), latLonReq1.getLongitude());
        GlobalCoordinates target = new GlobalCoordinates(latLonReq2.getLatitude(), latLonReq2.getLongitude());
        return new GeodeticCalculator().calculateGeodeticCurve(Ellipsoid.Sphere, source, target).getEllipsoidalDistance();
    }

    /**
     * latitude范围
     * @param latLon
     * @param range
     * @return
     */
    public static double[] getLatitudeRange(LatLonReq latLon, double range){
        double[] latitudeRange = {0.0,0.0};
        double oneLatitudeDistance = PI * R / 180;
        double latitude = latLon.getLatitude();
        double calculateLatitude = range / oneLatitudeDistance;
        latitudeRange[0] = latitude - calculateLatitude;
        latitudeRange[1] = latitude + calculateLatitude;
        return latitudeRange;
    }

    /**
     * longitude范围
     * @param latLon
     * @param range
     * @return
     */
    public static double[] getLongitudeRange(LatLonReq latLon, double range){
        double[] longitudeRange = {0.0,0.0};
        double latitude = latLon.getLatitude();
        double longitude = latLon.getLongitude();
        double longitudeEnd = longitude + 1.0;
        double oneLongitudeDistance = getDistance(latLon, new LatLonReq(latitude, longitudeEnd));
        double calculateLongitude = range / oneLongitudeDistance;
        longitudeRange[0] = longitude - calculateLongitude;
        longitudeRange[1] = longitude + calculateLongitude;
        return longitudeRange;
    }

    public static double[] getLongitudeRangeByLatLon(String latc, String lonc, String range){
        double dRange = 500;
        if(range != null){
            dRange = Double.parseDouble(range);
        }
        double dLatitude = Double.parseDouble(latc);
        double dLongitude = Double.parseDouble(lonc);
        LatLonReq latLonReq = new LatLonReq(dLatitude, dLongitude);
        return getLongitudeRange(latLonReq, dRange);
    }

    public static double[] getLatitudeRangeByLatLon(String latc, String lonc, String range){
        double dRange = 500;
        if(range != null){
            dRange = Double.parseDouble(range);
        }
        double dLatitude = Double.parseDouble(latc);
        double dLongitude = Double.parseDouble(lonc);
        LatLonReq latLonReq = new LatLonReq(dLatitude, dLongitude);
        return getLatitudeRange(latLonReq, dRange);
    }

    @Test
    public void testLonLat(){
        LatLonReq p1 = new LatLonReq(25.510474, 103.786537);
        p1.setRadius(300);
        LatLonReq p2 = new LatLonReq(25.512860,103.785110);
        p2.setRadius(240);
        double distance = DistanceUtil.getDistance(103.786537, 25.510474, 103.785110, 25.512860);
        /**
         * 角度先要通过Math.toRadians转换为弧度、然后Math.cos(x)传参，可计算出每个角度的值，取值范围在 -1 到 1
         * 90- 270  cos取值为负值  0-90  270-359 cos取值为正值  30度和330度的cos值相等 即 两个角度相加等于360度的取值相等
         *
         * 角度在象限不同，判断改加减的的经纬度
         * 象限一: 经度加差值，纬度加差值
         * 象限二: 经度加差值，纬度减差值
         * 象限三: 经度减差值，纬度减差值
         * 象限四: 经度减差值，纬度加差值
         */
        //TODO::先用角度跟固定距离，算出小区方向的另一个点的坐标
        //TODO::根据另一点坐标和两个小区的连线，然后通过叉乘算法，可以判断小区点在向量线段的哪一边，然后在判断角度，可确定他们是否相交
        //cos -- 临边
        double cosRange = Math.cos(Math.toRadians(p1.getRadius())) * 500;
        //sin -- 对边
        double sinRange = Math.sin(Math.toRadians(p1.getRadius())) * 500;
        double testRe = cosRange * cosRange + sinRange * sinRange;
        //300度角度的cosRange 等于 ---- 250.00000000000006  60-300的临边
        log.info("300度角度的cosRange 等于 ---- " + cosRange);
        //300度角度的sinRange 等于 ---- -433.0127018922193  60-300的对边
        log.info("300度角度的sinRange 等于 ---- " + sinRange);
        //300度角度的sin和cos平方和等于 ---- 250000.0
        log.info("300度角度的sin和cos平方和等于 ---- " + testRe);
        // 经度相同，1纬度差
        double oneLatitudeDistance = PI * R / 180;
        double latitude = p1.getLatitude();
        //纬度相同，1经度差
        double longitude = p1.getLongitude();
        double longitudeEnd = longitude + 1.0;
        double oneLongitudeDistance = getDistance(p1, new LatLonReq(latitude, longitudeEnd));
        //分情况获取经纬度
        //一象限： 临边-正 对边-正  经度加，纬度加   cos 临边 -> 纬度   sin 对边 -> 经度  60 : 临边cos  250 对边sin  433  cos 代表纬度， sin 代表经度
        //二象限： 临边-负 对边-正  经度加，纬度减   cos 临边 -> 纬度   sin 对边 -> 经度  120: 临边cos -250 对边sin  433  cos 代表纬度， sin 代表经度
        //三象限： 临边-负 对边-负  经度减，纬度减   cos 临边 -> 纬度   sin 对边 -> 经度  240: 临边cos -250 对边sin -433  cos 代表纬度， sin 代表经度
        //四象限： 临边-正 对边-负  经度减，纬度加   cos 临边 -> 纬度   sin 对边 -> 经度  360: 临边cos  250 对边sin -433  cos 代表纬度， sin 代表经度
        double diffLat = cosRange / oneLatitudeDistance;
        double diffLon = sinRange / oneLongitudeDistance;
        log.info("diffLat --- " + diffLat);
        log.info("diffLon --- " + diffLon);
        double reX = diffLat + latitude;
        double reY = longitude + diffLon;
        log.info("reX ---- 纬度" + reX);
        log.info("reY ---- 经度" + reY);
        double distance1 = DistanceUtil.getDistance(reY, reX, longitude, latitude);
        log.info("distance1 ---- 方向角度计算后的经纬度距离 --- " + distance1);
        double disCos = oneLatitudeDistance * (reX - latitude);
        log.info("disCos ---- " + disCos);
        double cosDe = disCos / distance1;
        log.info("cosDe --- 计算坐标点之间的cos -- " + cosDe);
        double acos = Math.acos(cosDe);
        log.info("计算坐标点之间的 acos ---- " + acos);
        double degrees = Math.toDegrees(acos);

//        if(reX > latitude && reY > longitude){
//            return degrees;
//        }
        if(reX > latitude && reY < longitude){
            degrees = 360 - degrees;
        }
        if(reX < latitude && reY > longitude){
            degrees = 180 - degrees;
        }
        if(reX < latitude && reY < longitude){
            degrees = 180 + degrees;
        }
        log.info("反向推算出来的degrees角度 --- " + degrees);
        long round = Math.round(degrees);
        log.info("反向推算出来的round角度 --- " + round);
        log.info("-- p1 --- lat---" + p1.getLatitude() + " ---- lon --- " + p1.getLongitude() +
                "reX --- " + reX + "--- reY --- " + reY);

        /*
        14:56:02.662 [main] INFO MyblogTest - 45度对应的弧度--- 0.7853981633974483
0.7071067811865476
14:56:02.663 [main] INFO MyblogTest - acos ---- 0.7853981633974483
14:56:02.663 [main] INFO MyblogTest - degrees ---- 45.0
14:56:02.663 [main] INFO MyblogTest - 90度对应的弧度--- 1.5707963267948966
6.123233995736766E-17
14:56:02.663 [main] INFO MyblogTest - 135度对应的弧度--- 2.356194490192345
-0.7071067811865475
14:56:02.663 [main] INFO MyblogTest - 180度对应的弧度--- 3.141592653589793
-1.0
14:56:02.664 [main] INFO MyblogTest - 225度对应的弧度--- 3.9269908169872414
-0.7071067811865477
14:56:02.664 [main] INFO MyblogTest - 270度对应的弧度--- 4.71238898038469
-1.8369701987210297E-16
14:56:02.664 [main] INFO MyblogTest - 315度对应的弧度--- 5.497787143782138
0.7071067811865474
14:56:02.664 [main] INFO MyblogTest - 359度对应的弧度--- 6.265732014659643
0.9998476951563913
14:56:02.665 [main] INFO MyblogTest - 30度对应的弧度--- 0.5235987755982988
0.8660254037844387
14:56:02.665 [main] INFO MyblogTest - 300度对应的弧度--- 5.235987755982989
0.5000000000000001
14:56:02.665 [main] INFO MyblogTest - 60度对应的弧度--- 1.0471975511965976
0.5000000000000001
14:56:02.665 [main] INFO MyblogTest - 330度对应的弧度--- 5.759586531581287
         */


    }

    /**
     * 计算方位角
     *
     * @param x1 lat1
     * @param y1 lng1
     * @param x2 lat2
     * @param y2 lng2
     * @return
     */
    public static double azimuthAngle(double x1, double y1, double x2, double y2) {
        double dx, dy, angle = 0;
        dx = x2 - x1;
        dy = y2 - y1;
        if (x2 == x1) {
            angle = Math.PI / 2.0;
            if (y2 == y1) {
                angle = 0.0;
            } else if (y2 < y1) {
                angle = 3.0 * Math.PI / 2.0;
            }
        } else if ((x2 > x1) && (y2 > y1)) {
            angle = Math.atan(dx / dy);
        } else if ((x2 > x1) && (y2 < y1)) {
            angle = Math.PI / 2 + Math.atan(-dy / dx);
        } else if ((x2 < x1) && (y2 < y1)) {
            angle = Math.PI + Math.atan(dx / dy);
        } else if ((x2 < x1) && (y2 > y1)) {
            angle = 3.0 * Math.PI / 2.0 + Math.atan(dy / -dx);
        }

        return (angle * 180 / Math.PI);
    }

    public static double GetAzimuth(double dN1, double dE1, double dN2, double dE2) {
        double dAzimuth = 0;

        dAzimuth = Math.atan2(dE2 - dE1, dN2 - dN1) * 180 / Math.PI;
        if (dAzimuth < 0)
            dAzimuth += 360;

        return dAzimuth;
    }

    @Test
    public void testRadiansRange(){

        /**
         * 11:21:13.585 [main] INFO MyblogTest - 45度对应的弧度--- 0.7853981633974483
         * 0.7071067811865476
         * 11:21:13.585 [main] INFO MyblogTest - 90度对应的弧度--- 1.5707963267948966
         * 6.123233995736766E-17
         * 11:21:13.586 [main] INFO MyblogTest - 135度对应的弧度--- 2.356194490192345
         * -0.7071067811865475
         * 11:21:13.587 [main] INFO MyblogTest - 180度对应的弧度--- 3.141592653589793
         * -1.0
         * 11:21:13.587 [main] INFO MyblogTest - 225度对应的弧度--- 3.9269908169872414
         * -0.7071067811865477
         * 11:21:13.587 [main] INFO MyblogTest - 270度对应的弧度--- 4.71238898038469
         * -1.8369701987210297E-16
         * 11:21:13.587 [main] INFO MyblogTest - 315度对应的弧度--- 5.497787143782138
         * 0.7071067811865474
         * 11:21:13.587 [main] INFO MyblogTest - 359度对应的弧度--- 6.265732014659643
         * 0.9998476951563913
         */
        double x = 45.0;
        //将角度转换成弧度值
        x = Math.toRadians(x);
        log.info("45度对应的弧度--- " + x);
        System.out.println(Math.cos(x));//0.7071067811865476
        double acos = Math.acos(0.7071067811865476);
        log.info("acos ---- " + acos);
        double degrees = Math.toDegrees(acos);
        log.info("degrees ---- " + degrees);

        x = 90.0;
        x = Math.toRadians(x);
        log.info("90度对应的弧度--- " + x);
        System.out.println(Math.cos(x));

        x = 135.0;
        x = Math.toRadians(x);
        log.info("135度对应的弧度--- " + x);
        System.out.println(Math.cos(x));

        x = 180.0;
        x = Math.toRadians(x);
        log.info("180度对应的弧度--- " + x);
        System.out.println(Math.cos(x));

        x = 225.0;
        x = Math.toRadians(x);
        log.info("225度对应的弧度--- " + x);
        System.out.println(Math.cos(x));

        x = 270;
        x = Math.toRadians(x);
        log.info("270度对应的弧度--- " + x);
        System.out.println(Math.cos(x));

        x = 315;
        x = Math.toRadians(x);
        log.info("315度对应的弧度--- " + x);
        System.out.println(Math.cos(x));

        x = 359;
        x = Math.toRadians(x);
        log.info("359度对应的弧度--- " + x);
        System.out.println(Math.cos(x));


        x = 30;
        x = Math.toRadians(x);
        log.info("30度对应的弧度--- " + x);
        System.out.println(Math.cos(x));

        x = 300;
        x = Math.toRadians(x);
        log.info("300度对应的弧度--- " + x);
        System.out.println(Math.cos(x));

        x = 60;
        x = Math.toRadians(x);
        log.info("60度对应的弧度--- " + x);
        System.out.println(Math.cos(x));

        x = 330;
        x = Math.toRadians(x);
        log.info("330度对应的弧度--- " + x);
        System.out.println(Math.cos(x));


        double cosRange = Math.cos(Math.toRadians(300)) * 500;
        //120度对边  120度对边
        double sinRange = Math.sin(Math.toRadians(300)) * 500;
        double testRe = cosRange * cosRange + sinRange * sinRange;

        //300度角度的cosRange 等于 ---- 250.00000000000006  60-300的临边
        log.info("临边cosRange 等于 ---- " + cosRange);
        //300度角度的sinRange 等于 ---- -433.0127018922193  60-300的对边
        log.info("对边的sinRange 等于 ---- " + sinRange);
        //300度角度的sin和cos平方和等于 ---- 250000.0
        log.info("300度角度的sin和cos平方和等于 ---- " + testRe);

        /**
         * 11:43:11.341 [main] INFO MyblogTest - 30度对应的弧度--- 0.5235987755982988
         * 0.8660254037844387
         * 11:43:11.341 [main] INFO MyblogTest - 300度对应的弧度--- 5.235987755982989
         * 0.5000000000000001
         * 11:43:11.341 [main] INFO MyblogTest - 60度对应的弧度--- 1.0471975511965976
         * 0.5000000000000001
         * 11:43:11.342 [main] INFO MyblogTest - 330度对应的弧度--- 5.759586531581287
         * 0.8660254037844384
         */

    }

    @Test
    public void testLog(){
        log.info("-------testLog------方法---日志");
        int x = 1 << 4;
        log.info("----x--1 << 4---" + x);//1001  1 0000
        String hi = "🍷Hellow";
        char c1 = hi.charAt(1);
        log.info("c1 index --- " + c1);
        char c0 = hi.charAt(0);
        int i1 = hi.codePointAt(0);
        int i2 = hi.codePointAt(1);
        log.info("查看码点 --- i1 --- " + i1 + "---i2-- " + i2 );
        String ii = new String();
        log.info("c0 index --- " + c0);
        log.info("hi长度 ---- " + hi.length());
        int i = hi.codePointCount(0, hi.length());
        log.info("码点数量 --- " + i);
        int[] ints = hi.codePoints().toArray();
        String str = new String ( ints , 0 , ints.length );
        log.info("str -------- " + str);



    }

    @Data
    class UserTest{
        String name;
        String age;
        String email;
        BigDecimal testBig;
    }

    @Test
    public void testForJavaStudy(){
        Date date = new Date();
        log.info("date --- " + date.toString());
        LocalDate now = LocalDate.now();
        Month month = now.getMonth();
        log.info("localDate --- month --- " + month.toString());
    }

    @Test
    public void testDate(){
        List<String> emptyList = Collections.EMPTY_LIST;
        emptyList.stream().forEach(item -> {
            log.info("item ----- " + item.toString());
        });
        log.info(emptyList.toString());
        List<String> hi = emptyList.stream().filter(item -> item.equals("hi")).collect(Collectors.toList());
        log.info("hi ---" + Arrays.toString(hi.toArray()));
    }


    @Test
    public void testEasyExcel(){
        String fileName = "H:\\润建\\图纸项目\\result_all_new_0118.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里每次会读取3000条数据 然后返回过来 直接调用使用数据就行
            //输出设计大表 和pmis表
        EasyExcel.read(fileName, ResultExcel.class, new ResultExcelListener()).sheet().doRead();
    }

    @Test
    public void testMapte(){
        /**
         * 室分新址后端输入字段map
         */
         Map<String, String> inlTemplateMap = new HashMap<String, String>(){{
            put("1","newPlanSiteNumber");
            put("2","logicSiteName");
            put("3","downTiltBaseSiteName");
            put("6","carrierProperties");
            put("9","bbuCuDuDeviceDetailInstallSite");
            put("10","bbuCuDuLongitude");
            put("11","bbuCuDuLatitude");
            put("13","rruAauIsProfitUsed");
            put("14","rruAauProfitUsedCoLocationLogicSite");
            put("15","productFactory");
            put("16","bbuNumber");
            put("17","deviceModel");
            put("25","deviceDetailInstallSiteBbu");
            put("26","longitudeRru");
            put("27","latitudeRru");
            put("29","rruAauNumber");
            put("30","rruAauModel");
            put("31","rruAauIsProfitUsed");
            put("32","rruAauProfitUsedCoLocationLogicSite");
            put("39","isUsedAau");
        }};

        String s = inlTemplateMap.get("40");
        log.info("sssssss- " + s);

        String fileNameZh = "nimabi.pdf";

        String substring = fileNameZh.substring(0, fileNameZh.length() - 4);

        log.info("文件名" + substring);

        File file = new File("H:\\润建\\图纸项目\\输出\\test?.pdf.xlsx");
        log.info("file" + file);



    }

    @Test
    public void testWriteExcel(){
        String property = System.getProperty("user.dir");
        log.info(property + "----------------");
        property = property + File.separator + "temp";
        File file = new File(property);
        if(!file.exists()){
            file.mkdir();
        }
        List<OutPutExcel> outPutExcels = new ArrayList<>();
        for(int i = 0; i < 2; i++){
            OutPutExcel outPutExcel = new OutPutExcel();
            outPutExcel.setSerialNumber("" + i);
            outPutExcel.setInstance("instances.get(i)");
            outPutExcel.setPropertyNameZh("propertyNameZhs.get(i)");
            outPutExcel.setRulesUseAttributeNameZh("rulesUseAttributeNameZhs.get(i)");
            outPutExcel.setDataSource("dataSources.get(i)");
            outPutExcel.setForwardPeople("forwardPeoples.get(i)");
            outPutExcel.setTemplate("templates.get(i)");
            outPutExcel.setRemark("remarks.get(i)");
            outPutExcels.add(outPutExcel);
        }
        String excelFileName = property + File.separator + "输出.xlsx";
        String sheetName = "表1";
        EasyExcel.write(excelFileName, OutPutExcel.class).sheet(sheetName).doWrite(outPutExcels);
        log.info("------------------------------");
    }





}
