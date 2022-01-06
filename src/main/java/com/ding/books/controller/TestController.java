package com.ding.books.controller;

import com.ding.books.model.entity.Book;
import com.ding.books.model.entity.Echars;
import com.ding.books.service.BookService;
import com.ding.books.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 测试下Echars
 * @Author 丁帅帅
 * @Date 21/12/24 07:43
 * @Version 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
   private BookService bookService;

    @RequestMapping(value = "/EcharsShow")
    public List<Echars> findById(Model model) {

        List<Echars> list = new ArrayList<Echars>();
        List<Book> allLimit = bookService.findAllLimit();
        for (Book book : allLimit) {
            list.add(new Echars(book.getBookname(),book.getNumber()));
        }
       /* list.add(new Echars("人类简史",50));
        list.add(new Echars("追寻生命的意义",26));
        list.add(new Echars("明朝那些事儿",35));
        list.add(new Echars("造彩虹的人",10));
        list.add(new Echars("秘密花园",72));
        list.add(new Echars("方向",12));
        list.add(new Echars("控方证人",12));
        list.add(new Echars("三生三世 十里桃花",12));*/

        return list;
    }

    //人数分布占比饼形图
    @RequestMapping("/getSetmealReport")
    public Result getSetmealReport(){
        //使用模拟数据测试使用什么样的java对象转为饼形图所需的json数据格式
        Map<String,Object> data = new HashMap<>();

        try{
           // List<Map<String,Object>> setmealCount = setmealService.findSetmealCount();
            List<Map<String,Object>> setmealCount = new ArrayList<>();
            Map<String,Object> map1 = new HashMap<>();
            Map<String,Object> map2 = new HashMap<>();
            Map<String,Object> map3 = new HashMap<>();
            Map<String,Object> map4 = new HashMap<>();
            Map<String,Object> map5 = new HashMap<>();

            map1.put("name","泰安");
            map1.put("value",10);

            map2.put("name","大阜阳");
            map2.put("value",30);

            map3.put("name","济南");
            map3.put("value",20);

            map4.put("name","北京");
            map4.put("value",15);

            map5.put("name","上海");
            map5.put("value",25);


            setmealCount.add(map1);
            setmealCount.add(map2);
            setmealCount.add(map3);
            setmealCount.add(map4);
            setmealCount.add(map5);
            data.put("setmealCount",setmealCount);

            List<String> setmealNames = new ArrayList<>();

            setmealNames.add("泰安");
            setmealNames.add("大阜阳");
            setmealNames.add("济南");
            setmealNames.add("北京");
            setmealNames.add("上海");
            data.put("setmealNames",setmealNames);
            return new Result(true, "获取成功",data);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"获取失败");
        }
    }
}
