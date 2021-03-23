package com.example.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
    //定义连接对象
    private static DataSource ds = null;
    //静态方法，配置数据库连接
    static{
        Properties pro = new Properties();
        //需要特别注意的是，之前没用maven时，pro文件是放在/src中
        //但是在maven中，需要将pro文件放在/main/resources中才可以被读取
        InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            pro.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //获取数据库连接
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    //释放资源
    public static void close(Statement stmt, Connection conn){
        if(stmt!=null){
            try {
                stmt.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    //重载释放资源操作
    public static void close(Statement stmt, Connection conn, ResultSet rs){
        close(stmt, conn);
        //释放第三个
        if(rs!=null){
            try {
                rs.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    //获取连接池方法
    public static DataSource getDataSource() {
        return ds;
    }

    public static void main(String[] args) throws SQLException {  //测试一下
        Connection conn = JDBCUtils.getConnection();
        System.out.println(conn);
    }
}

