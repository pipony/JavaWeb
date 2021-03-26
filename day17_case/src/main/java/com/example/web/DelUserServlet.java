package com.example.web;

import com.example.service.UserService;
import com.example.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/delUserServlet")
public class DelUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //2.获取欲删除的用户id
        String id = request.getParameter("id");
        //3.调用UserService的deleteUser方法进行删除
        UserService us = new UserServiceImpl();
        us.deleteUser(id);
        //4.跳转到“查看所有用户列表”的界面
        request.getRequestDispatcher("/userListServlet").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
