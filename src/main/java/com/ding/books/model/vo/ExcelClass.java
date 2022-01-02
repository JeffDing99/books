package com.ding.books.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description TODO
 * @Author 丁帅帅
 * @Date 21/12/21 20:42
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelClass implements Serializable {

    private Integer id;
    private Integer classid;
    private String classname;

}
