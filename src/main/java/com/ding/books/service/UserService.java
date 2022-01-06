package com.ding.books.service;

import com.ding.books.model.dto.AddUserRequest;
import com.ding.books.model.dto.EditUserRequest;
import com.ding.books.model.entity.User;
import com.ding.books.utils.PageResult;
import com.ding.books.utils.QueryPageBean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author 丁帅帅
 * @Date 21/12/11 16:23
 * @Version 1.0
 */
@Service
public interface UserService {
   int addUser(AddUserRequest user);

   int deleteUser(Integer id);

   int UpdateUser(EditUserRequest user);

   User findOneUser(Integer id);

   PageResult pageQuery(QueryPageBean queryPageBean);

    List<User> findAll();

   String findByPhoneAndPwd(String username, String password);

    Integer findByUsername(String username);

    User queryAllByUsername(String username);

    int addQQUser(User user);

    User findByOpenid(String openId);
}
