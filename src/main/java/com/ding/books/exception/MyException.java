package com.ding.books.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 自定义异常类
 * @Author 丁帅帅
 * @Date 21/12/24 08:15
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyException extends RuntimeException{

    private boolean flag;//执行结果，true为执行成功 false为执行失败
    private String message;//返回结果信息


}
