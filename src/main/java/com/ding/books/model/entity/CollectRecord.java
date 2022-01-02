package com.ding.books.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 收藏表
 * @Author 丁帅帅
 * @Date 21/12/22 16:38
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("collectrecord")
public class CollectRecord implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer collectid;
    private Integer bookid;
    private Integer userid;
    private Date lenddate;

}
