package com.ding.books.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.books.model.dto.ClassBook;
import com.ding.books.model.entity.ClassInfo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Description TODO
 * @Author 丁帅帅
 * @Date 21/12/23 09:24
 * @Version 1.0
 */
@Mapper
@Repository
public interface ClassMapper extends BaseMapper<ClassInfo> {
    Page<ClassBook> selectByCondition(String queryString);
}
