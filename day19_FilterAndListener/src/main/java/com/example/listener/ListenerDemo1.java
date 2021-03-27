package com.example.listener;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@WebListener
public class ListenerDemo1 implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    /**
     * 监听ServletContext对象创建的。ServletContext对象服务器启动后自动创建。
     *
     * 在服务器启动后自动调用
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
        System.out.println("initialized...");
        //可在该方法中“加载资源文件”
        //1.获取ServletContext对象
        ServletContext sc = sce.getServletContext();
        //2.加载资源文件
        String contextConfigLocation = sc.getInitParameter("contextConfigLocation");
        //3.获取真实路径
        String realPath = sc.getRealPath(contextConfigLocation);
        //4.加载进内存
        try {
            FileInputStream fis = new FileInputStream(realPath);
            System.out.println(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * 在服务器关闭后，ServletContext对象被销毁。当服务器正常关闭后该方法被调用
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
        System.out.println("destroyed...");
    }
}
