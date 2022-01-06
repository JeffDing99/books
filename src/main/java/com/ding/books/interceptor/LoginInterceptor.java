package com.ding.books.interceptor;

import com.ding.books.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Description TODO
 * @Author 丁帅帅
 * @Date 21/12/09 22:11
 * @Version 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String  token = (String) request.getSession().getAttribute("token");
        if(token != null ) {
            Claims claims = JWTUtils.checkJWT(token);
            if(claims !=null){
                Integer userId = (Integer)claims.get("id");
                String name = (String) claims.get("name");

                request.setAttribute("user_id",userId);
                request.setAttribute("name",name);

                return true;
            }
        }

       // sendJsonMessage(response,new Result(false,"请先登录!!!"));
        response.setContentType("text/html; charset=utf-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        pw.print("<script>alert('请先登录!!!');window.location.href='/index.html';</script>");
        response.sendRedirect("index.html");
        return false;
    }




    /**
     * 响应json数据给前端
     * @param response
     * @param obj
     */
   /* public static void sendJsonMessage(HttpServletResponse response, Object obj){

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.print(objectMapper.writeValueAsString(obj));
            writer.close();
            response.flushBuffer();
        }catch (Exception e){
            e.printStackTrace();
        }


    }*/




    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

