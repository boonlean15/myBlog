package com.cheney.concurrentdesign;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Copy-On-Write 示例
 */
public class CowDemo {

    //Key:接口名
    //Value:路由集合
    ConcurrentHashMap<String, CopyOnWriteArraySet<Router>> rt = new ConcurrentHashMap<>();

    /**
     * 根据接口名获取路由表
     * @param iface
     * @return
     */
    public Set<Router> get(String iface){
        return rt.get(iface);
    }
    /**
     * 删除路由
     */
    public void remove(Router router) {
        Set<Router> set = rt.get(router.iface);
        if (set != null) {
            set.remove(router);
        }
    }

    /**
     * 增加路由
     * @param router
     */
    public void add(Router router) {
        Set<Router> set = rt.computeIfAbsent(router.iface, r -> new CopyOnWriteArraySet<>());
        set.add(router);
    }

}
