package com.ding.books.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ding.books.mapper.BookMapper;
import com.ding.books.model.dto.AddBook;
import com.ding.books.model.dto.EditBook;
import com.ding.books.model.entity.Book;
import com.ding.books.service.BookService;
import com.ding.books.utils.PageResult;
import com.ding.books.utils.QueryPageBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description TODO
 * @Author 丁帅帅
 * @Date 21/12/09 23:26
 * @Version 1.0
 */
@Transactional
@Service
public class BookServiceImpl implements BookService {


    @Autowired
    private BookMapper bookMapper;


    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();//查询条件
        //完成分页查询，基于mybatis框架提供的分页助手插件完成
        PageHelper.startPage(currentPage,pageSize);
        //select * from books limit 0,10
        Page<Book> page = bookMapper.selectByCondition(queryString);
        long total = page.getTotal();
        List<Book> rows = page.getResult();
        return new PageResult(total,rows);
    }

    public int addBook(AddBook addBook) {

       // return bookMapper.addBook(books);

        Book book = new Book();
        BeanUtils.copyProperties(addBook,book);
        return bookMapper.insert(book);
    }




    //根据ID删除图书
    public void deleteById(Integer id) {

        bookMapper.deleteById(id);
    }

    public void edit(EditBook editBook) {
        Book book = new Book();
       // BeanUtils.copyProperties(editBook,book);
        book.setNumber(editBook.getNumber());
        book.setBookname(editBook.getBookname());
        book.setAuthor(editBook.getAuthor());
        book.setClassid(editBook.getClassid());
        book.setIntroduction(editBook.getIntroduction());
        book.setPublish(editBook.getPublish());
        bookMapper.update(book,new UpdateWrapper<Book>().eq("bookid",editBook.getBookid()));
    }

    @Override
    public List<Book> findAll() {
        return bookMapper.selectList(null);
    }

    @Override
    public List<Book> findAllLimit() {
        List<Book> allLimit = bookMapper.findAllLimit();
        return allLimit;
    }


    @Override
    public Book findBookById(Integer bookid) {
        Book book = bookMapper.selectOne(new QueryWrapper<Book>().eq("bookid",bookid));
        return book;
    }

    @Override
    public void lendBook(Integer bookid) {


        Book book = findBookById(bookid);
        book.setNumber(book.getNumber()-1);
        bookMapper.update(book, new UpdateWrapper<Book>().eq("bookid", bookid));
    }
}
