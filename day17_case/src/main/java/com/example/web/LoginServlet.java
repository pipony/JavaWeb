package com.example.web;
/*
处理登录逻辑
 */
import com.example.dao.UserDao;
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

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //0.设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //1.获取request参数Map
        Map<String, String[]> paras = request.getParameterMap();
        HttpSession session = request.getSession();
        //先明确一下，是先判断验证码是否正确。若正确，才判断用户名密码的正确性
        //因为，判断用户名密码需要访问数据库，开销较大
        //2.判断验证码是否正确
        //2.1 获取正确的验证码
        String realCode = (String) session.getAttribute("code");
        //注意，为了保证验证码是一次性的，在获取一次以后就删除该属性
        session.removeAttribute("code");
        //2.2 比较输入的验证码是否正确，还要忽略大小写
        //由于可能因为上一次的删除没有新的code属性add上，所以realCode可能为null
        boolean flag1 = false;
        boolean flag2 = false;
        if (realCode==null) flag2 = true;
        else{
            flag1 = realCode.equalsIgnoreCase(paras.get("code")[0]);
        }
        //3.若验证码错误，跳转到登录界面，并提示“验证码错误”
        if (!flag1||flag2){
            //存储错误信息到request域对象
            request.setAttribute("login_msg", "验证码错误");
            //请求转发到登录界面
            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
        }
        //4.若验证码正确，进一步验证用户名和密码
        //4.1 封装为用户类
        User user = new User();
        try {
            BeanUtils.populate(user, paras);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //4.2 调用login方法查询，并返回User对象
        UserService us = new UserServiceImpl();
        User findUser = us.login(user);
        //4.3 判断查找的对象是否为空，并分别跳转到不同的界面
        if (findUser == null){ //跳转回登录界面，并提示“用户名或密码错误”
            //存储错误信息到request域对象
            request.setAttribute("login_msg", "用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
        else {  //验证成功，重定向到成功界面，并记录用户信息到session
            session.setAttribute("user", findUser);  //这时候就不能将信息存到request对象中，因为重定向是两次请求
            response.sendRedirect(request.getContextPath()+"/index.jsp");  //重定向需要虚拟目录
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
