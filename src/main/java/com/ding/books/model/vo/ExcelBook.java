package com.ding.books.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @Author 丁帅帅
 * @Date 21/12/19 15:54
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelBook {
    private Integer bookid;
    private String bookname;
    private String author;
    private String publish;
    private String introduction;
    private Integer classid;
    private Integer number;
}
