package com.ding.books.controller;

import com.ding.books.model.dto.QueryPageBeanUsername;
import com.ding.books.model.entity.LendRecord;
import com.ding.books.model.vo.ExcelLend;
import com.ding.books.service.LendService;
import com.ding.books.utils.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author 丁帅帅
 * @Date 21/12/19 17:05
 * @Version 1.0
 */
@RestController
@RequestMapping("/lend")
public class LendController {

    @Autowired
    private LendService lendService;



    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findPageUser")
    public PageResult findPageUser(@RequestBody QueryPageBeanUsername queryPageBean){
        PageResult pageResult =lendService.pageQueryUser(queryPageBean);

        return pageResult;
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult =lendService.pageQuery(queryPageBean);

        return pageResult;
    }

    /**
     * 删除
     * @param lendid
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Integer lendid){
        try{
            lendService.deleteLend(lendid);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.DELETE_Lend_FAIL);
        }
        return  new Result(true, MessageConstant.DELETE_Lend_SUCCESS);
    }


    @RequestMapping("/findLendById")
    public Result findLendById(Integer lendid){
        try{
            LendRecord lendRecord =lendService.findLendById(lendid);
            return  new Result(true, MessageConstant.QUERY_Lend_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.QUERY_Lend_FAIL);
        }
    }

    /**
     * 归还图书
     * @param lendid
     * @return
     */
    @RequestMapping("/back")
    public Result back(Integer lendid){
        //int i=1/0;
       //全局异常不补获try catch中的异常
        try{

            lendService.back(lendid);

            //throw new RuntimeException();


           return  new Result(true, MessageConstant.Back_Lend_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.Back_Lend_FAIL);
        }
    }

    @RequestMapping("/export")
    public void export( HttpServletResponse response){
        try{

            List<LendRecord> list =lendService.findAll();
            //System.out.println(list.toString());
            String[] columnNames=new String[]{"lendid","bookid","userid","lenddate","backdate","number"};
            String[] keys=new String[]{"lendid","书的编号","用户id","借书日期","应还日期","数量"};
            List<ExcelLend> excelLends =new ArrayList<>();
            for (LendRecord lendRecord : list) {
                ExcelLend excelLend = new ExcelLend();
                BeanUtils.copyProperties(lendRecord,excelLend);
                excelLends.add(excelLend);
            }

            ExcelUtils.export(response,"借阅信息表",excelLends,columnNames,keys,"借阅信息");
            //return  new Result(true, MessageConstant.EXPORT_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败
            //return new Result(false, MessageConstant.EXPORT_FAIL);
        }
    }



}
