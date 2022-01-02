package com.ding.books;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class BooksApplicationTests {

    @Test
    void contextLoads() {
    }

   @Test
    void testdate(){
       Date date = new Date();
       String format = DateUtil.format(date, "yyyy/MM/dd");
       System.out.println(format);

       String formatDate = DateUtil.formatDate(date);

       String formatDateTime = DateUtil.formatDateTime(date);

       DateTime newDate3 = DateUtil.offsetHour(date, -3);

       System.out.println(newDate3);

       DateTime dateTime = DateUtil.offsetMonth(date, 1);
       System.out.println(dateTime);
   }



}
