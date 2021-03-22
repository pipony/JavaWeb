package com.example.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "OutputServlet", value = "/OutputServlet")
public class OutputServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        response.setContentType("text/html;charset=utf-8");
        //2.获取输出字节流
        ServletOutputStream os = response.getOutputStream();
        //3.输出数据
        os.write("hello 字节流".getBytes("utf-8"));  //注意哦，输入应是byte[]，而且还要设置一下编码方式
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
