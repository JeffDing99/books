package com.ding.books.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ding.books.model.entity.User;
import com.ding.books.service.UserService;
import com.ding.books.utils.JWTUtils;
import com.ding.books.utils.QqHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Slf4j
@Controller
public class LoginController {


    @Autowired
    private UserService userService;

    /**
     * QQ互联中提供的 appid 和 appkey
     */
    @Value("${qq.oauth.appid}")
    public String APPID;
    @Value("${qq.oauth.appkey}")
    public String APPKEY;
    @Value("${qq.oauth.url}")
    public String URL;




    /**
     * 请求授权页面
     */
    @GetMapping(value = "/LoginByQQ")
    public String qqAuth(HttpSession session) {
        // 用于第三方应用防止CSRF攻击
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        session.setAttribute("state", uuid);

        // Step1：获取Authorization Code
        String url = "https://graph.qq.com/oauth2.0/authorize?response_type=code" +
                "&client_id=" + APPID +
                "&redirect_uri=" + URLEncoder.encode(URL) +
                "&state=" + uuid;

        return "redirect:"+url;
    }


    /**
     * 授权回调
     */
    @GetMapping(value = "/qq/callback")
    public String qqCallback(HttpServletRequest request, HashMap<String, Object> map, Model model, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        // 得到Authorization Code
        String code = request.getParameter("code");
        // 我们放在地址中的状态码
        String state = request.getParameter("state");
        // 验证信息
        String uuid = (String) session.getAttribute("state");

        // 验证信息我们发送的状态码
        if (null != uuid) {
            // 状态码不正确，直接返回登录页面
            if (!uuid.equals(state)) {
                return "index";
            }
        }

        // Step2：通过Authorization Code获取Access Token
        String url = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code" +
                "&client_id=" + APPID +
                "&client_secret=" + APPKEY +
                "&code=" + code +
                "&redirect_uri=" + URL;
        String access_token = QqHttpClient.getAccessToken(url);

        // Step3: 获取回调后的openID
        url = "https://graph.qq.com/oauth2.0/me?access_token=" + access_token;
        String openId = QqHttpClient.getOpenID(url);

        // Step4：获取QQ用户信息
        url = "https://graph.qq.com/user/get_user_info?access_token=" + access_token +
                "&oauth_consumer_key=" + APPID +
                "&openid=" + openId;

        // 得到用户信息
        JSONObject object = null;
        object = QqHttpClient.getUserInfo(url);
        /**
         * 获取到用户信息之后，就该写你自己的业务逻辑了
         */
         Map map1 = JSON.parseObject(String.valueOf(object));
        //System.out.println("这个是用JSON类的parseObject来解析JSON字符/串!!!");
       /* for (Object obj : map1.keySet()){
            System.out.println("key为："+obj+"值为："+map1.get(obj));

        }*/
        //map.put("nickname",map1.get("nickname"));
        User user = null;
        log.info("openid{}"+openId);
        if (openId!=null){
            User dbUser = userService.findByOpenid(openId);
            if (dbUser != null) {
                user = new User(openId,map1.get("nickname"), map1.get("gender"), map1.get("city"), map1.get("figureurl_1"));
                //userService.UpdateQQUser(user);\
                String token = JWTUtils.geneJsonWebToken(user);
                session.setAttribute("token",token);
                response.sendRedirect("http://ding99.top/pages/main_user.html?username="+URLEncoder.encode(user.getUsername(),"UTF-8")+"&head_img="+user.getHead_img());

            }else {
                user = new User(openId,map1.get("nickname"), map1.get("gender"), map1.get("city"), map1.get("figureurl_1"));
                String token = JWTUtils.geneJsonWebToken(user);
                session.setAttribute("token",token);
                userService.addQQUser(user);
                response.sendRedirect("http://ding99.top/pages/main_user.html?username="+URLEncoder.encode(user.getUsername(),"UTF-8")+"&head_img="+user.getHead_img());

            }
        } /*else {
            user = new User(openId,map1.get("nickname"), map1.get("gender"), map1.get("city"), map1.get("figureurl_qq_1"));
            userService.addQQUser(user);
            response.sendRedirect("http://ding99.top/pages/main_user.html?username="+URLEncoder.encode(user.getUsername(),"UTF-8")+"&head_img="+user.getHead_img());

        }*/



       // response.sendRedirect("AfterLogin.html");
        //response.sendRedirect("http://ding99.top/AfterLogin.html");

        // return "AfterLogin";
        return null;
    }



}
