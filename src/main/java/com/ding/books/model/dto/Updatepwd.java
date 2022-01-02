package com.ding.books.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @Author 丁帅帅
 * @Date 21/12/24 08:28
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Updatepwd {

    private String username;
    private  String oldpwd;
    private String newpwd;
}
