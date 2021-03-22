package com.example.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "WriterServlet", value = "/WriterServlet")
public class WriterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        response.setContentType("text/html;charset=utf-8");
        //2.获取输出字符流
        PrintWriter pw = response.getWriter();
        //3.输出数据
        pw.write("hello 字符流");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
