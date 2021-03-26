package com.example.web;

import com.example.domain.User;
import com.example.service.UserService;
import com.example.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/addUserServlet")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //2.获取用户输入的所有信息
        Map<String, String[]> paras = request.getParameterMap();
        //3.封装为User对象，使用BeanUtils
        User newUser = new User();
        try {
            BeanUtils.populate(newUser, paras);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //4.调用UserService保存
        UserService us = new UserServiceImpl();
        us.addUser(newUser);
        //5.跳转回UserListServlet，查看所有用户的信息列表
        request.getRequestDispatcher("/userListServlet").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
