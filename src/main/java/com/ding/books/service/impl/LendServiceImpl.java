package com.ding.books.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ding.books.mapper.BookMapper;
import com.ding.books.mapper.LendMapper;
import com.ding.books.mapper.UserMapper;
import com.ding.books.model.dto.LendBook;
import com.ding.books.model.dto.QueryPageBeanUsername;
import com.ding.books.model.entity.Book;
import com.ding.books.model.entity.LendRecord;
import com.ding.books.model.entity.User;
import com.ding.books.service.BookService;
import com.ding.books.service.LendService;
import com.ding.books.utils.PageResult;
import com.ding.books.utils.QueryPageBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Description TODO
 * @Author 丁帅帅
 * @Date 21/12/19 17:48
 * @Version 1.0
 */
@Transactional
@Service
@Slf4j
public class LendServiceImpl implements LendService {

    @Autowired
    private LendMapper lendMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookService bookService;

    @Autowired
    private UserMapper userMapper;



    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();//查询条件

        //完成分页查询，基于mybatis框架提供的分页助手插件完成
        PageHelper.startPage(currentPage,pageSize);
        //select * from books limit 0,10
        Page<LendBook> page = lendMapper.selectByCondition(queryString);
        long total = page.getTotal();
        List<LendBook> rows = page.getResult();

        return new PageResult(total,rows);
    }

    @Override
    public void deleteLend(Integer lendid) {
        int i = lendMapper.deleteById(lendid);
    }

    @Override
    public LendRecord findLendById(Integer lendid) {

        LendRecord lendRecord = lendMapper.selectById(lendid);
        return lendRecord;
    }

    @Override
    public void back(Integer lendid) {

        LendRecord lendRecord = findLendById(lendid);

        Book book = bookService.findBookById(lendRecord.getBookid());
        book.setNumber(book.getNumber()+1);
        bookMapper.update(book, new UpdateWrapper<Book>().eq("bookid", lendRecord.getBookid()));
        //int i=1/0;

        int q = lendMapper.deleteById(lendid);
    }

    @Override
    public List<LendRecord> findAll() {
        List<LendRecord> lendRecords = lendMapper.selectList(null);

        return lendRecords;
    }

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
        Page<LendBook> page = lendMapper.selectByConditionUser(user.getId(),queryString);
        long total = page.getTotal();
        List<LendBook> rows = page.getResult();
      /*  System.out.println("========="+total);
        System.out.println("+++++++++"+rows.size());
        for (LendBook row : rows) {
            System.out.println(row.toString());
        }*/
        return new PageResult(total,rows);
    }

    @Override
    public int addLendRecord(Integer bookid, Integer userid) {
        LendRecord lendRecord = new LendRecord();
        Date date = new Date();
        lendRecord.setLenddate(date);
        lendRecord.setNumber(1);
        lendRecord.setBookid(bookid);
        lendRecord.setUserid(userid);
        lendRecord.setBackdate(DateUtil.offsetMonth(date,1));
        int insert = lendMapper.insert(lendRecord);

        return insert;
    }

    @Override
    public List<LendRecord> findLendByBookid(Integer bookid) {

        List<LendRecord> lendByBookid = lendMapper.findLendByBookid(bookid);
        return  lendByBookid;
    }
}
