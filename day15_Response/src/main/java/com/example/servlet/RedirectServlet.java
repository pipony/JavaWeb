package com.example.servlet;
/*
实现重定向，到RedirectServlet2
 */
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RedirectServlet", value = "/RedirectServlet")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //实现重定向
        response.setStatus(302);
        response.setHeader("location", "/day15_Response/RedirectServlet2");
        response.sendRedirect("/day15_Response/RedirectServlet2");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);

    }
}
