package com.example.web;

import com.example.domain.PageBean;
import com.example.domain.User;
import com.example.service.UserService;
import com.example.service.impl.UserServiceImpl;
import com.sun.javaws.IconUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //2.获取浏览器请求过来的currentPage和rows
        //判断两个参数为空的情况（比如从index.jsp调过来时），赋予默认值
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");
        if (currentPage == null || "".equals(currentPage)) currentPage = "1";
        if (rows == null || "".equals(rows)) rows = "5";
        //还要获取条件查询的参数
        //需要注意的是：经过测试，由于GET方法得到的参数会中文乱码，因此需要额外处理一下
        Map<String, String[]> condition = request.getParameterMap();
        if (request.getMethod().equals("GET")){
            for (String key: condition.keySet()){
                if (condition.get(key)!=null  && !"".equals(condition.get(key))){
                    String oldValue = condition.get(key)[0];
                    condition.get(key)[0] = new String(oldValue .getBytes("iso8859-1"),"utf-8");
                }
            }
        }

        System.out.println("condition");
        for (String key: condition.keySet()){
            System.out.println(key+": "+condition.get(key)[0]);
        }
        //3.调用Service的findUserByPage方法获得PageBean
        UserService us = new UserServiceImpl();
        PageBean<User> pb = new PageBean();
        pb = us.findUserByPage(currentPage, rows, condition);
        //4.将PageBean存入request中
        request.setAttribute("pb", pb);
        request.setAttribute("condition", condition);
        //5.跳转到“查看所有用户列表”的界面
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
