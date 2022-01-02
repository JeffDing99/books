package com.ding.books.controller;

import com.ding.books.model.dto.AddBook;
import com.ding.books.model.dto.EditBook;
import com.ding.books.model.entity.Book;
import com.ding.books.model.entity.CollectRecord;
import com.ding.books.model.entity.LendRecord;
import com.ding.books.model.entity.User;
import com.ding.books.model.vo.ExcelBook;
import com.ding.books.service.BookService;
import com.ding.books.service.CollectService;
import com.ding.books.service.LendService;
import com.ding.books.service.UserService;
import com.ding.books.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


/**
 * @Description 图书
 * @Author 丁帅帅
 * @Date 21/12/09 23:21
 * @Version 1.0
 */
@Slf4j
@RestController
//@RolesAllowed({"admin"})
@RequestMapping("/book")
public class BookController {

    //controller 调 service 层
    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private LendService lendService;

    @Autowired
    private CollectService collectService;

    /**
     * 借书
     * @param bookid
     * @return
     */
    //@PutMapping("/lend")
    @RequestMapping("/lend")
    public  Result lendBook(@RequestParam("bookid") Integer bookid,@RequestParam("username") String username){
        //System.out.println("------>"+username);
        try{
            Book book = bookService.findBookById(bookid);
            if (book.getNumber() > 0) {
                bookService.lendBook(bookid);
                User user = userService.queryAllByUsername(username);
                lendService.addLendRecord(bookid,user.getId());
            } else {
                return new Result(false, MessageConstant.LEND_BOOK_FAIL);
            }
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.LEND_BOOK_FAIL);
        }
        return  new Result(true, MessageConstant.LEND_BOOK_SUCCESS);
    }

    @RequestMapping("/collect")
    public  Result collectBook(@RequestParam("bookid") Integer bookid,@RequestParam("username") String username){
        //System.out.println("------>"+username);
        try{
            User user = userService.queryAllByUsername(username);
           List<CollectRecord> collectRecords= collectService.findCollectBookByBookidandUsrid(bookid,user.getId());
           if ( collectRecords.size()>0) {
               return new Result(false, "收藏失败,此书已被收藏");
           }else {
               collectService.addCollectBook(bookid,user.getId());
           }

        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.Collect_BOOK_FAIL);
        }
        return  new Result(true, MessageConstant.Collect_BOOK_SUCCESS);
    }
    /**
     * 新增图书
     * @param addBook
     * @return
     */
    //@PreAuthorize("hasAnyRole('user')") // 只能user角色才能访问该方法
    //@RequestMapping("/add")
    @PostMapping("/add")
    public Result add(@RequestBody AddBook addBook){
        try{
            bookService.addBook(addBook);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.ADD_Book_FAIL);
        }
        return  new Result(true, MessageConstant.ADD_Book_SUCCESS);
    }


    /**
     * 图书分页查询
     * @param queryPageBean
     * @return
     */
   // @PreAuthorize("hasAnyRole('admin')") // 只能user角色才能访问该方法
    //@PostMapping("/findPage")
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean, HttpServletRequest request,HttpServletResponse response){

        PageResult pageResult = bookService.pageQuery(queryPageBean);
        return pageResult;
    }

    /**
     * 删除图书
     * @param bookid
     * @return
     */
   // @PreAuthorize("hasAnyRole('admin')") // 只能user角色才能访问该方法
    //@RequestMapping("/delete")
    @DeleteMapping("/delete")
    public Result delete(Integer bookid){
        try{
            List<LendRecord> lendRecord = lendService.findLendByBookid(bookid);
            if (lendRecord != null) {
                return new Result(false, "此书还有被借图书,删除失败!!!");
            }
            bookService.deleteById(bookid);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.DELETE_Book_FAIL);
        }
        return  new Result(true, MessageConstant.DELETE_Book_SUCCESS);
    }

    /**
     * 编辑图书
     * @param editBook
     * @return
     */
  // @RequestMapping("/edit")
    @PutMapping("/edit")
    public Result edit(@RequestBody EditBook editBook){
        try{
            bookService.edit(editBook);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.EDIT_Book_FAIL);
        }
        return  new Result(true, MessageConstant.EDIT_Book_SUCCESS);
    }

    @GetMapping("/findBookById")
    public Result findBookById(Integer bookid){
        try{
            Book book = bookService.findBookById(bookid);
            return  new Result(true, MessageConstant.QUERY_Book_SUCCESS,book);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.QUERY_Book_FAIL);
        }
    }
  /*
    @RequestMapping("/findAll")
    public Result findAll(){
        try{
            List<Book> list = bookService.findAll();
            return  new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS,list);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.ADD_CHECKITEM_SUCCESS);
        }
    }*/



    @RequestMapping("/export")
    public void export(HttpServletResponse response) {
        try{

            List<Book> list =bookService.findAll();
            //System.out.println(list.toString());
            String[] columnNames=new String[]{"bookid","bookname","author","publish","introduction","classid","number"};
            String[] keys=new String[]{"图书号","图书名","作者","出版社","简介","类别号","数量"};
            List<ExcelBook> excelBooks =new ArrayList<>();
            for (Book book : list) {
                ExcelBook excelBook = new ExcelBook();
                BeanUtils.copyProperties(book,excelBook);
                excelBooks.add(excelBook);
            }

            ExcelUtils.export(response,"图书信息表",excelBooks ,columnNames,keys,"图书信息");
            //return  new Result(true, MessageConstant.EXPORT_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败
            //return new Result(false, MessageConstant.EXPORT_FAIL);
        }
    }
}
