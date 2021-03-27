package com.example.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Cindy.H
 * @description: 卖电脑的代理对象
 * @date 2021-03-27 11:35:24
 */
public class ProxyComputer{
    public static void main(String[] args) {
        //1.创建真实对象
        Lenovo lenovo = new Lenovo();
        //2.创建动态代理的增强lenovo对象
        /*
        输入参数：
                1. 类加载器：真实对象.getClass().getClassLoader()
                2. 接口数组：真实对象.getClass().getInterfaces()
                3. 处理器：new InvocationHandler()
         */
        SaleComputer proxy = (SaleComputer) Proxy.newProxyInstance(lenovo.getClass().getClassLoader(),
                lenovo.getClass().getInterfaces(), new InvocationHandler() {
                    //proxy调用的方法执行时都变成执行invoke
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //输出invoke的输入参数看看
                        //System.out.println(method);
                        //System.out.println(args[0]);

                        if (method.getName().equals("sale")){
                            //进行代理增强（输入参数、返回值、方法体）
                            double money = (double) args[0];
                            money = money * 0.8;
                            System.out.println("专车接你来");
                            //使用method.invoke进行方法调用，参数（真实对象，args）
                            String s = (String) method.invoke(lenovo, money);
                            System.out.println("专车送你回家");
                            return s+"_鼠标垫";
                        }
                        else {
                            Object obj = method.invoke(lenovo, args);  //无增强的执行原方法
                            return obj;
                        }
                    }
                });

        //调用方法
        System.out.println(proxy.sale(1000));
        System.out.println();
        proxy.show();

    }
}
