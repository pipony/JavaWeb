package com.example.web;

import com.example.domain.User;
import com.example.service.UserService;
import com.example.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/userListServlet")
public class UserListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //0.设置编码
        request.setCharacterEncoding("utf-8");
        //1.调用service层的findAll()方法
        UserService userService = new UserServiceImpl();
        List<User> userList = userService.findAll();
        //2.将userList存到request域对象中
        request.setAttribute("userList", userList);
        //3.转发到list.jsp中展示
        RequestDispatcher rd = request.getRequestDispatcher("/list.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
