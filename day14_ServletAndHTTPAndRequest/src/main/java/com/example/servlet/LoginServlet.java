package com.example.servlet;

import com.example.dao.UserDao;
import com.example.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/demo1")   //注意Web的W是大写！！！
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.设置编码
        req.setCharacterEncoding("utf-8");

        /*//2.获取username和password
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //3.将两者封装为一个User类
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);*/

        //值得注意的是，若获取的参数很多种，那么操作特别麻烦
        //所以为了简化封装，利用map一步到位，接下来使用BeanUtils来替代上述操作
        Map<String, String[]> paras = req.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, paras);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //4.调用UserDao的login方法查询，并返回User对象
        User findUser = new UserDao().login(user);
        //5.判断查找的对象是否为空，并分别跳转到不同的界面
        if (findUser == null){
            req.getRequestDispatcher("/failServlet").forward(req, resp);
        }
        else {
            //因为成功页面要用到用户信息，所以将user加到req的域对象中
            req.setAttribute("user", user);
            req.getRequestDispatcher("/successServlet").forward(req, resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
