package com.example.servlet;
/*
学习ServletContext
 */
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletContext1", value = "/ServletContext1")
public class ServletContext1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取ServletContext
        ServletContext sc = this.getServletContext();
        //2.获取MIME类型
        System.out.println(sc.getMimeType("a.jpg"));
        //3.测试域对象的范围
        //在此servlet中设置属性
        sc.setAttribute("attr1", 3);
        //4.获取文件的真实路径
        /*
        新建四个文件，路径如下：
        /webapp/a.txt
        /webapp/WEB-INF/b.txt
        /src/c.txt
        /resources/d.txt
         */
        System.out.println(sc.getRealPath("/a.txt"));
        System.out.println(sc.getRealPath("/WEB-INF/b.txt"));
        System.out.println(sc.getRealPath("/WEB-INF/classes/c.txt"));
        System.out.println(sc.getRealPath("d.txt"));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
