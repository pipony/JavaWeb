package com.example.servlet;

import com.example.utils.DownLoadUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(name = "DownloadServlet", value = "/DownloadServlet")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        //2.获取欲下载的文件名
        String path = request.getParameter("filename");
        //但是request.setCharacterEncoding也只能解决POST参数的中文乱码问题
        //当方法是GET时，需要以下处理才行
        if(request.getMethod().equals("GET")){
            path = new String(path.
                    getBytes("ISO-8859-1"), "UTF-8");
        }
        //3.使用字节输入流加载文件到内存中
        //3.1 获取文件的（真实）服务器位置
        //我们把文件都放在/webapp/img中
        ServletContext sc = this.getServletContext();
        String filePath = sc.getRealPath("/img/"+path);
        //3.2 用字节流关联文件
        FileInputStream fis = new FileInputStream(filePath);

        //4.指定response响应头
        //4.1 设置响应头类型
        String type = sc.getMimeType(path);
        response.setHeader("content-type", type);
        //4.2 解决中文文件名下载的时候乱码问题
        //4.2.1 获取客户端浏览器的版本 user-agent
        String agent = request.getHeader("user-agent");
        //4.2.2 利用导入的工具类（无需自己编写）来编码文件名
        path = DownLoadUtils.getFileName(agent, path);
        //4.3 设置响应头打开方式
        //这里需要注意的是，path的参数位置是到时候下载文件时默认的文件名，因此我们要到path
        //而不是真实路径filePath
        response.setHeader("content-disposition", "attachment;filename="+path);

        //5.将数据写入response输出流
        ServletOutputStream sos = response.getOutputStream();
        byte[] bytes = new byte[1024 * 8];
        int len = 0;
        while((len = fis.read(bytes))!=-1){
            sos.write(bytes);
        }
        //关闭文件流
        fis.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
