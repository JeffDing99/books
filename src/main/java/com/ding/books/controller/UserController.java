package com.ding.books.controller;


import com.ding.books.exception.MyException;
import com.ding.books.model.dto.AddUserRequest;
import com.ding.books.model.dto.EditUserRequest;
import com.ding.books.model.dto.LoginRequest;
import com.ding.books.model.dto.Updatepwd;
import com.ding.books.model.entity.User;
import com.ding.books.model.vo.ExcelUser;
import com.ding.books.service.UserService;
import com.ding.books.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private static Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    /**
     * 修改密码
     * @param updatepwd
     * @return
     */
    @RequestMapping("/updatepwd")
    public Result updatepwd(@RequestBody Updatepwd updatepwd) {
        String oldpwd = CommonUtils.MD5(updatepwd.getOldpwd());
        User user = userService.queryAllByUsername(updatepwd.getUsername());
        if (oldpwd.equals(user.getPassword())){
            user.setPassword(CommonUtils.MD5(updatepwd.getNewpwd()));
            EditUserRequest userRequest = new EditUserRequest();
          BeanUtils.copyProperties(user,userRequest);
          edit(userRequest);
          throw new MyException(true,"修改成功");
           // return new Result(true,"修改成功");
        }

        return new Result(false,"修改失败,请检查原密码是否正确");
    }
    @RequestMapping("/register")
    public Result register(@RequestBody AddUserRequest user){
        try{
            Integer i =userService.findByUsername(user.getUsername());
             logger.info("i的值"+i);
            if(i!=0){
                return new Result(false, "该用户已存在,注册失败");
            }else {
                userService.addUser(user);
            }
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.Register_User_FAIL);
        }
        return  new Result(true, MessageConstant.Register_User_SUCCESS);
    }


   // @PreAuthorize("hasAnyRole('admin')")
    @RequestMapping("/login")
    public Result login(@RequestBody LoginRequest loginRequest, HttpSession session){

        String token = userService.findByPhoneAndPwd(loginRequest.getUsername(), loginRequest.getPassword());
        session.setAttribute("token",token);
        //System.out.println(" request.setAttribute(\"token\",token);"+token);
        if (token == null) {
            logger.info("登录"+MessageConstant.Login_User_FAIL);
             return new Result(false, MessageConstant.Login_User_FAIL,token);

         } else {
            logger.info("登录"+MessageConstant.Login_User_SUCCESS);
             return  new Result(true, MessageConstant.Login_User_SUCCESS,token);
         }

    }

    /**
     * 退出
     * @return
     */
    @RequestMapping("/logout")
    public Result logout(HttpServletRequest request){

        if (true) {
           // Object token = request.getSession().getAttribute("token");
           // log.info("token1{}"+token);
            request.getSession().removeAttribute("token");
           // Object token1 = request.getSession().getAttribute("token");
           // log.info("token{}"+token1);
            return new Result(true, MessageConstant.Logout_User_SUCCESS);
        } else {
            return  new Result(false, MessageConstant.Logout_User_FAIL);
        }

    }
    /**
     * 新增用户
     * @param user
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody AddUserRequest user){
        try{
           userService.addUser(user);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.ADD_User_FAIL);
        }
        return  new Result(true, MessageConstant.ADD_User_SUCCESS);
    }

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult =userService.pageQuery(queryPageBean);
        return pageResult;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Integer id){
        try{
           userService.deleteUser(id);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.DELETE_User_FAIL);
        }
        return  new Result(true, MessageConstant.DELETE_User_SUCCESS);
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @RequestMapping("/edit")
    public Result edit(@RequestBody EditUserRequest user){
        try{
           userService.UpdateUser(user);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.EDIT_User_FAIL);
        }
        return  new Result(true, MessageConstant.EDIT_User_SUCCESS);
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
        try{
            User user =userService.findOneUser(id);
            return  new Result(true, MessageConstant.QUERY_User_SUCCESS,user);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.QUERY_User_FAIL);
        }
    }

    @RequestMapping("/export")
    public void export( HttpServletResponse response){
        try{

            List<User> list =userService.findAll();
            //System.out.println(list.toString());
            String[] columnNames=new String[]{"id","username","sex","phone","city"};
            String[] keys=new String[]{"id","姓名","性别","手机","城市"};
            List<ExcelUser> excelUsers =new ArrayList<>();
            for (User user : list) {
                ExcelUser excelUser = new ExcelUser();
                BeanUtils.copyProperties(user,excelUser);
                excelUsers.add(excelUser);
            }

            ExcelUtils.export(response,"用户信息表",excelUsers,columnNames,keys,"用户信息");
            //return  new Result(true, MessageConstant.EXPORT_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败
            //return new Result(false, MessageConstant.EXPORT_FAIL);
        }
    }

    @RequestMapping("/findAll")
    public Result findAll(HttpServletResponse response){
        try{
            List<User> list =userService.findAll();
            return  new Result(true, MessageConstant.QUERY_User_SUCCESS,list);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.QUERY_User_FAIL);
        }
    }

}
