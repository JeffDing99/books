package com.ding.books.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String openid;
    private String username;
    private String password;
    private String sex;
    private String head_img;
    private String phone;
    private String city;
    private String role;
    /*public User(String username, String password, String sex, String phone, String city) {

        this.username = username;
        this.password = password;
        this.sex = sex;
        this.phone = phone;
        this.city = city;
    }*/

    public User(String openId, Object nickname, Object gender, Object city, Object figureurl_1) {
        this.openid=openId;
        this.username = (String) nickname;
        this.sex = (String) gender;
        this.city = (String) city;
        this.head_img= (String) figureurl_1;
    }
}
