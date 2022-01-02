package com.ding.books.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @Author 丁帅帅
 * @Date 21/12/17 11:21
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddBook {
    private String bookname;
    private String author;
    private String publish;
    private String introduction;
    private Integer classid;
    private Integer number;
}
