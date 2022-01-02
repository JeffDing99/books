package com.ding.books.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @Author 丁帅帅
 * @Date 21/12/19 10:25
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelUser {

    private Integer id;
    private String username;
    private String sex;
    private String phone;
    private String city;

}
