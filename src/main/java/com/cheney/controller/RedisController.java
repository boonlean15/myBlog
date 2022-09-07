package com.cheney.controller;

import com.cheney.entity.UserEntity;
import com.cheney.utils.RedisUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author linch
 * @create 2021/10/27 16:13
 */


@RestController
public class RedisController {
    /**
     * redis中存储的过期时间60s
     */
    private static int ExpireTime = 60;

    @Resource
    RedisUtils redisUtils;

    @GetMapping("/set")
    public boolean redisSet(String key, String value){
        UserEntity userEntity =new UserEntity();
        userEntity.setUsername("liulihan");
        userEntity.setPassword("linchuangwei");
        userEntity.setName("liulihan");
        userEntity.setSex("female");
        userEntity.setAge(String.valueOf(18));
        userEntity.setCreateTime(new Date());
        return redisUtils.set(key,value);
    }

    @GetMapping("/get")
    public Object redisGet(String key){
        return redisUtils.get(key);
    }

    @GetMapping("expire")
    public boolean redisExpire(String key){
        return redisUtils.expire(key,ExpireTime);
    }

}
