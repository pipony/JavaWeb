package com.example.cookie;
/*
简单测试cookie，接收cookie
 */
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/servletCookie2")
public class ServletCookie2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        int i = 1;
        for (Cookie cookie: cookies){
            String attr = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookie"+i+"( attr: "+attr+", value: "+value+")");
            i++;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
