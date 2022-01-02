package com.ding.books.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 封装查询条件
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryPageBeanUsername implements Serializable{
    private Integer currentPage;//页码
    private Integer pageSize;//每页记录数
    //private String queryString;//查询条件
    private String bookname;
    private String username;

}
