package com.example.web;

import com.example.service.UserService;
import com.example.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/delSelectedServlet")
public class DelSelectedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //2.获取欲删除的用户id数组，注意用getParameterValues
        String[] ids = request.getParameterValues("uid");
        //3.调用UserService的deleteUsers方法
        UserService us = new UserServiceImpl();
        us.deleteUsers(ids);
        //4.重定向到userListServlet
        response.sendRedirect(request.getContextPath()+"/userListServle");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
