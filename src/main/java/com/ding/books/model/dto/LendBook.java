package com.ding.books.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description value = {"hibernateLazyInitializer", "handler"}
 * @Author 丁帅帅
 * @Date 21/12/19 17:12
 * @Version 1.0
 */
//@JsonIgnoreProperties(value = {"lisi", "wang"})


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LendBook implements Serializable {

    private Integer lendid;
    private Integer bookid;
    private String bookname;
    private String username;
    //@JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date lenddate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date backdate;
    private Integer number;
    //private Integer  lisi;
    //private Integer  wang;

    @Override
    public String toString() {
        return "LendBook{" +
                "bookid=" + bookid +
                ", bookname='" + bookname + '\'' +
                ", username='" + username + '\'' +
                ", number=" + number +
                '}';
    }
}
