package com.example.cookie;
/*
利用cookie实现一个应用：
若用户是第一次登陆，则显示“欢迎新用户”
若不是第一次登陆，则显示“上次登陆时间是xxx”
 */
import javax.management.StandardEmitterMBean;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

@WebServlet("/ServletCookie3")
public class ServletCookie3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //尝试获取登录时间相关cookie(名称就叫"lastTime")
        Cookie[] cookies = request.getCookies();
        Cookie timeCookie = null;
        for (Cookie cookie: cookies){
            if (cookie.getName().equals("lastTime")){
                timeCookie = cookie;
                break;
            }
        }
        //获取页面的字符输出流
        PrintWriter pw = response.getWriter();
        //获取当前时间并转化为字符串
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowDatetime = tempDate.format(new java.util.Date());
        //若没有找到已有时间cookie，则显示欢迎新用户并存储时间到timeCookie中
        if (timeCookie == null){
            pw.write("Welcome, new user!");
            timeCookie = new Cookie("lastTime", nowDatetime);
            timeCookie.setMaxAge(60*60*24); //存活时间设为1天
            response.addCookie(timeCookie);
        }
        else {  //若有找到，则显示上次登录时间并更新timeCookie
            String lastDateTime = timeCookie.getValue();
            pw.write("your last visit time is: "+lastDateTime);
            timeCookie.setValue(nowDatetime);
            timeCookie.setMaxAge(60*60*24); //存活时间设为1天
            response.addCookie(timeCookie);  //注意，虽然浏览器端已有timeCookie，但我们已经更新
            //了这个cookie，所以需要重新addCookie一下。否则，浏览器中对应的cookie值不变。
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
