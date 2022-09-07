package com.cheney.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author linch
 * @create 2021/10/27 16:18
 */
@Data
public class UserEntity implements Serializable {
    private String username;
    private String name;
    private String sex;
    private String age;
    private String password;
    private Date createTime;
}
