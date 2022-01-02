package com.ding.books.controller;

import com.ding.books.model.dto.QueryPageBeanUsername;
import com.ding.books.model.entity.CollectRecord;
import com.ding.books.model.entity.LendRecord;
import com.ding.books.model.vo.ExcelCollect;
import com.ding.books.service.CollectService;
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
 * @Date 21/12/22 00:02
 * @Version 1.0
 */
@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService collectService;



    /**
     * 用户分页查询
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findPageUser")
    public PageResult findPageUser(@RequestBody QueryPageBeanUsername queryPageBean){
        PageResult pageResult =collectService.pageQueryUser(queryPageBean);

        return pageResult;
    }

    /**
     * 管理员分页查询
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult =collectService.pageQuery(queryPageBean);

        return pageResult;
    }

    /**
     * 删除
     * @param collectid
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Integer collectid){
        try{
            collectService.deleteCollect(collectid);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.DELETE_Collect_FAIL);
        }
        return  new Result(true, MessageConstant.DELETE_Collect_SUCCESS);
    }






    @RequestMapping("/export")
    public void export( HttpServletResponse response){
        try{

            List<CollectRecord> list =collectService.findAll();
            //System.out.println(list.toString());
            String[] columnNames=new String[]{"collectid","bookid","userid","lenddate"};
            String[] keys=new String[]{"collectid","书的编号","用户id","收藏日期"};
            List<ExcelCollect> excelCollects =new ArrayList<>();
            for (CollectRecord collectRecord : list) {
               // ExcelLend excelLend = new ExcelLend();
                ExcelCollect excelCollect = new ExcelCollect();
                BeanUtils.copyProperties(collectRecord,excelCollect);
                excelCollects.add(excelCollect);
            }

            ExcelUtils.export(response,"收藏信息表",excelCollects,columnNames,keys,"收藏信息");
            //return  new Result(true, MessageConstant.EXPORT_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败
            //return new Result(false, MessageConstant.EXPORT_FAIL);
        }
    }

}
