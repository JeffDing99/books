package com.ding.books.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description TODO
 * @Author 丁帅帅
 * @Date 21/12/21 20:42
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelLend implements Serializable {

    private Integer lendid;
    private Integer bookid;
    private Integer userid;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date lenddate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date backdate;
    private Integer number;

}
