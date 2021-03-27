package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

@WebFilter("/*")
public class DirtyWordsFilter implements Filter {
    private List<String> dirtyWords = new ArrayList<>();
    public void init(FilterConfig config) throws ServletException {
        //获取脏话数据（由于只需加载一次）
        try {
            InputStream inputStream = DirtyWordsFilter.class.getClassLoader().getResourceAsStream("dirtyWords.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            //读取文件以获取脏话列表
            String word;
            while ((word = br.readLine()) !=null){
                dirtyWords.add(word);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //创建代理对象，增强getParameter方法功能
        ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(request.getClass().getClassLoader(),
                request.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //1.增强req.getParameter方法
                        //1.1 判断method是否为getParameter
                        if (method.getName().equals("getParameter")){
                            //1.2 获取参数值
                            String value = (String) method.invoke(request, args);
                            //1.3 判断是否包含dirty words
                            for (String word: dirtyWords){
                                if (value.contains(word)){
                                    value = value.replaceAll(word, "***");
                                }
                            }
                            //1.4 返回改过的值
                            return value;
                        }
                        //2.method不是getParameter，则正常invoke
                        return method.invoke(request, args);
                    }
                });
        //放行
        chain.doFilter(proxy_req, response);
    }
}
