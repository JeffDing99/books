package com.ding.books.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.books.model.entity.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

    Page<User> selectByCondition(String queryString);

    User findByPhoneAndPwd(String username, String password);

    int findByUsername(String username);

    User queryAllByUsername(String username);

    User findByOpenid(@Param("openid") String openid);
}
