package com.ding.books.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @Author 丁帅帅
 * @Date 21/12/23 09:30
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassBook {

    private Integer id;
    private Integer classid;
    private String classname;

}
