package com.ding.books.service;

import com.ding.books.model.dto.AddBook;
import com.ding.books.model.dto.EditBook;
import com.ding.books.model.entity.Book;
import com.ding.books.utils.PageResult;
import com.ding.books.utils.QueryPageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description TODO
 * @Author 丁帅帅
 * @Date 21/12/09 23:25
 * @Version 1.0
 */
//@Transactional(readOnly = true)
@Transactional
@Service
public interface BookService {

    PageResult pageQuery(QueryPageBean queryPageBean);

    void deleteById(Integer id);


   // Book findById(Integer id);


 // @Transactional(readOnly = false)
 int addBook(AddBook addBook);

 Book findBookById(Integer bookid);

 void lendBook(Integer bookid);

 void edit(EditBook editBook);

 List<Book> findAll();

 List<Book> findAllLimit();

}
