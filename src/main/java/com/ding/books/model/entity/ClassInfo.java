package com.ding.books.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 图书类别号
 * @Author 丁帅帅
 * @Date 21/12/23 09:13
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("ClassInfo")
public class ClassInfo {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer classid;
    private String classname;

}
