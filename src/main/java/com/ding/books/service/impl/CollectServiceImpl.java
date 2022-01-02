package com.ding.books.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ding.books.mapper.CollectMapper;
import com.ding.books.mapper.UserMapper;
import com.ding.books.model.dto.CollectBook;
import com.ding.books.model.dto.QueryPageBeanUsername;
import com.ding.books.model.entity.CollectRecord;
import com.ding.books.model.entity.User;
import com.ding.books.service.CollectService;
import com.ding.books.utils.PageResult;
import com.ding.books.utils.QueryPageBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Description TODO
 * @Author 丁帅帅
 * @Date 21/12/22 17:07
 * @Version 1.0
 */
@Transactional
@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectMapper collectMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageResult pageQueryUser(QueryPageBeanUsername queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getBookname();//查询条件
        String username = queryPageBean.getUsername();
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        //System.out.println(username);
        //System.out.println("------->"+user.getId());
        //System.out.println("------->"+queryString);
        //完成分页查询，基于mybatis框架提供的分页助手插件完成
        PageHelper.startPage(currentPage,pageSize);
        //select * from books limit 0,10
        Page<CollectBook> page = collectMapper.selectByConditionUser(user.getId(),queryString);
        long total = page.getTotal();
        List<CollectBook> rows = page.getResult();

        return new PageResult(total,rows);
    }

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();//查询条件

        //完成分页查询，基于mybatis框架提供的分页助手插件完成
        PageHelper.startPage(currentPage,pageSize);
        //select * from books limit 0,10
        Page<CollectBook> page = collectMapper.selectByCondition(queryString);
        long total = page.getTotal();
        List<CollectBook> rows = page.getResult();

        return new PageResult(total,rows);
    }

    @Override
    public void deleteCollect(Integer collectid) {
           collectMapper.deleteById(collectid);
    }



    @Override
    public List<CollectRecord> findAll() {
        List<CollectRecord> collectRecords = collectMapper.selectList(null);
        return collectRecords;
    }

    @Override
    public void addCollectBook(Integer bookid, Integer id) {
       // LendRecord lendRecord = new LendRecord();
        CollectRecord collectRecord = new CollectRecord();
        Date date = new Date();
        collectRecord.setLenddate(date);
        collectRecord.setBookid(bookid);
        collectRecord.setUserid(id);
        collectMapper.insert(collectRecord);
    }

    @Override
    public List<CollectRecord> findCollectBookByBookidandUsrid(Integer bookid, Integer id) {
        List<CollectRecord> collectRecords= collectMapper.findCollectBookByBookidandUsrid(bookid,id);
        return collectRecords;
    }
}
