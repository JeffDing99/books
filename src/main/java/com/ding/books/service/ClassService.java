package com.ding.books.service;

import com.ding.books.model.entity.ClassInfo;
import com.ding.books.utils.PageResult;
import com.ding.books.utils.QueryPageBean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author 丁帅帅
 * @Date 21/12/23 09:22
 * @Version 1.0
 */
@Service
public interface ClassService {
    PageResult pageQuery(QueryPageBean queryPageBean);

    List<ClassInfo> findAll();
}
