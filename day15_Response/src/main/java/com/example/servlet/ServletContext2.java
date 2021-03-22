package com.example.servlet;
/*
测试ServletContext的功能：域对象
 */
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletContext2", value = "/ServletContext2")
public class ServletContext2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取ServletContext对象
        ServletContext sc = this.getServletContext();
        //2.尝试获取域对象的attr1的属性值
        System.out.println("开始输出域对象的属性值");
        System.out.println(sc.getAttribute("attr1"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
