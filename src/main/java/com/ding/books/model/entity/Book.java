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
public class Book implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer bookid;
    private String bookname;
    private String author;
    private String publish;
    private String introduction;
    private Integer classid;
    private Integer number;
    private String bookimg;
}
