package com.ding.books.service.impl;

import com.ding.books.mapper.UserMapper;
import com.ding.books.model.dto.AddUserRequest;
import com.ding.books.model.dto.EditUserRequest;
import com.ding.books.model.entity.User;
import com.ding.books.service.UserService;
import com.ding.books.utils.CommonUtils;
import com.ding.books.utils.JWTUtils;
import com.ding.books.utils.PageResult;
import com.ding.books.utils.QueryPageBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description TODO
 * @Author 丁帅帅
 * @Date 21/12/11 16:23
 * @Version 1.0
 */
@Transactional
@Service

public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper  userMapper;

    private  static Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Override
    public int addUser(AddUserRequest addUserRequest) {


        //User user = new User(addUserRequest.getUsername(),CommonUtils.MD5(addUserRequest.getPassword()),addUserRequest.getSex(),addUserRequest.getPhone(),addUserRequest.getCity());
        User user = new User();
        addUserRequest.setPassword(CommonUtils.MD5(addUserRequest.getPassword()));
        BeanUtils.copyProperties(addUserRequest,user);
        return userMapper.insert(user);
    }

    @Override
    public int deleteUser(Integer id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int UpdateUser(EditUserRequest editUserRequest) {

        //User user = new User(editUserRequest.getId(),editUserRequest.getUsername(),,editUserRequest.getSex(),editUserRequest.getPhone(),editUserRequest.getCity());
        User user = new User();
        BeanUtils.copyProperties(editUserRequest,user);
        return userMapper.updateById(user);
    }

    @Override
    public User findOneUser(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();//查询条件
        //完成分页查询，基于mybatis框架提供的分页助手插件完成
        PageHelper.startPage(currentPage,pageSize);
        //select * from books limit 0,10
        Page<User> page = userMapper.selectByCondition(queryString);
        long total = page.getTotal();
        List<User> rows = page.getResult();
        return new PageResult(total,rows);
    }

    @Override
    public List<User> findAll() {

        return userMapper.selectList(null);
    }

    @Override
    public String findByPhoneAndPwd(String username, String password) {

        User user = userMapper.findByPhoneAndPwd(username, CommonUtils.MD5(password));
        if(user == null){
            return null;

        }else {
            String token = JWTUtils.geneJsonWebToken(user);
            return token;
        }

    }


    @Override
    public Integer findByUsername(String username) {
       /* if (userMapper.findByUsername(username)==0 || (Integer)userMapper.findByUsername(username)==null) {
            return 0;
        }*/
        logger.info("userMapper.findByUsername(username)---->"+userMapper.findByUsername(username));
        //System.out.println("111111+>>>>>>>>"+userMapper.findByUsername(username));
        return userMapper.findByUsername(username);

    }

    @Override
    public User queryAllByUsername(String username) {
        return userMapper.queryAllByUsername(username);
    }

    @Override
    public int addQQUser(User user) {


       // addUserRequest.setPassword(CommonUtils.MD5(addUserRequest.getPassword()));
       // BeanUtils.copyProperties(addUserRequest,user);
        return userMapper.insert(user);
    }

    @Override
    public User findByOpenid(String openId) {
        return userMapper.findByOpenid(openId);
    }
}
