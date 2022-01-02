package com.ding.books.model.entity;

import lombok.Data;

/**
 * @Description TODO
 * @Author 丁帅帅
 * @Date 21/12/14 17:01
 * @Version 1.0
 */
@Data
public class UserInfo {
    private int id;
    private String username;
    private String password;
    private String role;
}

