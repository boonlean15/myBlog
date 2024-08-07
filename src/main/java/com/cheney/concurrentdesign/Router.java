package com.cheney.concurrentdesign;

import java.util.Objects;

/**
 * 重写了 equals 方法，这样 CopyOnWriteArraySet 的 add() 和 remove() 方法才能正常工作
 */
//路由信息
public final class Router{
    public final String  ip;
    public final Integer port;
    public final String  iface;

    //构造函数
    public Router(String ip,
                  Integer port, String iface){
        this.ip = ip;
        this.port = port;
        this.iface = iface;
    }
    //重写equals方法
    public boolean equals(Object obj){
        if (obj instanceof Router) {
            Router r = (Router)obj;
            return iface.equals(r.iface) &&
                    ip.equals(r.ip) &&
                    port.equals(r.port);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, port, iface);
    }
}
