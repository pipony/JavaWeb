package indi.cindy.jdbc2.main;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


/*
基于C3P0技术使用数据库连接池技术
 */
public class Demo1 {
    public static void main(String[] args) throws SQLException {
        //1.导入jar包
        //2.定义配置文件c3p0.properties
        //3.获取数据库连接对象
        DataSource ds = new ComboPooledDataSource();
        //4.获取数据库连接
        Connection cn = ds.getConnection();
        //5.输出连接信息
        System.out.println(cn);
        //6.释放连接
        cn.close();
    }

}
