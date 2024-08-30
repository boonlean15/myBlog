package com.cheney.concurrentdesign;

import java.util.Objects;
import java.util.concurrent.*;

/**
 * balking模式 多线程操作的if(与GuardedObject的区别在于balking不用等待if条件为真)
 */
public class BalkingDemo {
    /**
     文件自动保存 balking模式的经典实现
     */
    boolean changed = false;

    //自动存盘操作
    void autoSave(){
        synchronized (this){
            if(!changed){
                return;
            }
            changed = false;
        }
        //存盘操作
        //省略实现
        System.out.println("存盘");
    }

    //编辑操作
    void edit(){
        System.out.println("编辑操作");
        change();
    }

    void change(){
        synchronized (this){
            changed = true;
        }
    }

    /**
     * volatile实现balking模式 对原子性没有要求的场景
     */


    public class RouteTable{
        //Key:接口名
        //Value:路由集合
        ConcurrentHashMap<String, CopyOnWriteArraySet<Router>> rt = new ConcurrentHashMap<>();

        //路由表是否发生变化
        volatile boolean rChanged = false;
        //将路由表写入本地文件的线程池
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        //启动定时任务
        //将变更后的路由表写入本地文件
        public void startLocalSaver(){
            //scheduleWithFixedDelay保证只有一个线程在执行保存到本地路由
            ses.scheduleWithFixedDelay(()-> autoSaveRouteTable(), 1, 1, TimeUnit.MINUTES);
        }

        void autoSaveRouteTable(){
            if(!rChanged){
                return;
            }
            rChanged = false;

            System.out.println("//将路由表写入本地文件\n" +
                    "    //省略其方法实现");
        }

        //删除路由
        void remove(Router router){
            CopyOnWriteArraySet<Router> routers = rt.get(router.iface);
            if(!Objects.isNull(routers)){
                routers.remove(router);
                rChanged = true;//路由表已发生变化
            }
        }

        //增加路由
        void add(Router router){
            CopyOnWriteArraySet<Router> routers = rt.computeIfAbsent(router.iface, r -> new CopyOnWriteArraySet<Router>());
            routers.add(router);
            rChanged = true;//路由表已发生变化
        }

    }


}
