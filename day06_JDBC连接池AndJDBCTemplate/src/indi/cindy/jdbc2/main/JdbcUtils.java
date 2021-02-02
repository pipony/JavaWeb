package indi.cindy.jdbc2.main;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/*
基于Druid连接技术实现数据库连接的工具类（连接，释放资源，获取连接池的方法）
 */
public class JdbcUtils {
    //定义连接对象
    private static DataSource ds = null;
    //静态方法，配置数据库连接
    static{
        Properties pro = new Properties();
        InputStream is = Demo2.class.getClassLoader().getResourceAsStream("druid.properties");
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
        Connection conn = JdbcUtils.getConnection();
        System.out.println(conn);
    }
}
