package com.ding.books.service.impl;

import com.ding.books.mapper.ClassMapper;
import com.ding.books.model.dto.ClassBook;
import com.ding.books.model.entity.ClassInfo;
import com.ding.books.service.ClassService;
import com.ding.books.utils.PageResult;
import com.ding.books.utils.QueryPageBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description TODO
 * @Author 丁帅帅
 * @Date 21/12/23 09:22
 * @Version 1.0
 */
@Transactional
@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassMapper classMapper;

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();//查询条件
        //完成分页查询，基于mybatis框架提供的分页助手插件完成
        PageHelper.startPage(currentPage,pageSize);
        //select * from books limit 0,10
        Page<ClassBook> page = classMapper.selectByCondition(queryString);
        long total = page.getTotal();
        List<ClassBook> rows = page.getResult();
        return new PageResult(total,rows);
    }

    @Override
    public List<ClassInfo> findAll() {

        List<ClassInfo> classInfos = classMapper.selectList(null);
       /* for (ClassInfo classInfo : classInfos) {
            System.out.println(classInfo.toString());
        }*/
        return  classInfos;
    }
}
