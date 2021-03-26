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

@WebServlet("/updateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //2.获取修改后的参数
        Map<String, String[]> paras = request.getParameterMap();
        //3.封装到User中
        User user = new User();
        try {
            BeanUtils.populate(user, paras);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //4.调用UserService的updateUser方法
        UserService us = new UserServiceImpl();
        us.updateUser(user);
        //5.重定向“查看所有用户列表”的界面，用以在该界面中回显用户信息
        response.sendRedirect(request.getContextPath()+"/userListServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
