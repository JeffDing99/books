package com.ding.books.exception;

import com.ding.books.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 全局异常
 * @Author 丁帅帅
 * @Date 21/12/24 00:39
 * @Version 1.0
 */
@RestControllerAdvice
@Slf4j
public class CustomExtHandler {



    //捕获全局异常,处理所有不可知的异常
    @ExceptionHandler(value=Exception.class)
    Object handleException(Exception e, HttpServletRequest request){
        log.error("url {}, msg {}",request.getRequestURL(), e.getMessage());
        //log.error("错误:{}",request.getRequestURL(), e.getMessage());
       /* Map<String, Object> map = new HashMap<>();
        map.put("code", 100);
        map.put("msg", e.getMessage());
        map.put("url", request.getRequestURL());*/
       // return new Result(false,"有异常");
        if(e instanceof MyException){
            MyException myException =  (MyException)e;
            return new Result(myException.isFlag(),myException.getMessage());
        }else{
            return new Result(false,"全局异常");
        }
    }

}
