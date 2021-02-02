package indi.cindy.jdbc2.main;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/*
基于Druid技术使用数据库连接池技术
 */
public class Demo2 {
    public static void main(String[] args) throws Exception {
        //1.导入jar包
        //2.定义配置文件
        //3.加载配置文件
        Properties pro = new Properties();
        InputStream is = Demo2.class.getClassLoader().getResourceAsStream("druid.properties");
        pro.load(is);
        //4.获取数据库连接对象
        DataSource ds = DruidDataSourceFactory.createDataSource(pro);
        //5.获取数据库连接
        Connection cn = ds.getConnection();
        System.out.println(cn);
    }
}
