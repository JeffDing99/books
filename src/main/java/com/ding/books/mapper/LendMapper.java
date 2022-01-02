package com.ding.books.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.books.model.dto.LendBook;
import com.ding.books.model.entity.LendRecord;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description TODO
 * @Author 丁帅帅
 * @Date 21/12/19 17:44
 * @Version 1.0
 */

@Mapper
@Repository
public interface LendMapper extends BaseMapper<LendRecord> {

    Page<LendBook> selectByCondition(String queryString);

    Page<LendBook> selectByConditionUser(@Param("id") Integer id,@Param("bookname") String bookname);

    List<LendRecord> findLendByBookid(@Param("bookid") Integer bookid);
}
