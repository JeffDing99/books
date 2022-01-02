package com.ding.books.service;

import com.ding.books.model.dto.QueryPageBeanUsername;
import com.ding.books.model.entity.CollectRecord;
import com.ding.books.utils.PageResult;
import com.ding.books.utils.QueryPageBean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author 丁帅帅
 * @Date 21/12/22 17:07
 * @Version 1.0
 */

@Service
public interface CollectService {
    PageResult pageQueryUser(QueryPageBeanUsername queryPageBean);

    PageResult pageQuery(QueryPageBean queryPageBean);

    void deleteCollect(Integer collectid);



    List<CollectRecord> findAll();

    void addCollectBook(Integer bookid, Integer id);

    List<CollectRecord> findCollectBookByBookidandUsrid(Integer bookid, Integer id);
}
