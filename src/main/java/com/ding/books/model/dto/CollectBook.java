package com.ding.books.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description TODO
 * @Author 丁帅帅
 * @Date 21/12/22 16:50
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectBook implements Serializable {

    private Integer collectid;
    private Integer bookid;
    private String bookname;
    private String username;
    private Date lenddate;

}
