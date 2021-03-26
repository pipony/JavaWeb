package com.example.web;

import com.example.domain.User;
import com.example.service.UserService;
import com.example.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //2.获取用户id
        String id = request.getParameter("id");
        //3.找到该用户
        UserService us = new UserServiceImpl();
        User user = us.findUserById(id);
        //4.将user存到request中
        request.setAttribute("findUser", user);
        //5.转发到update.jsp
        request.getRequestDispatcher("/update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
