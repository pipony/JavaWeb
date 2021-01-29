package indi.cindy.jdbc.main;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/*
JDBC工具类，简化代码
主要思想：将连接的数据集情况写到配置文件中，建立一个统一的类以实现数据集的连接与释放
结论：以后只需直接调用这些接口来实现jdbc的连接和释放，我们只需关注重要的部分（即sql的执行部分）
 */
public class JdbcUtils {
    private static String url = null;
    private static String user = null;
    private static String password = null;
    private static String driver = null;
    //1.读取配置文件，因为只需执行一次，故static
    static{
        //读取配置文件
        Properties pro = new Properties();
        ClassLoader cl = indi.cindy.jdbc.main.JdbcUtils.class.getClassLoader();
        InputStream is = cl.getResourceAsStream("pro.properties");
        try {
            pro.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取配置文件中的信息
        url = pro.getProperty("url");
        user = pro.getProperty("user");
        password = pro.getProperty("password");
        driver = pro.getProperty("driver");
        //注册驱动（其实这个mysql5之后的驱动jar包可以省略，但为了统一就加上吧）
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //2.连接操作,并返回连接对象
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
    //3.释放资源操作
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
    //3.重载释放资源操作
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

}
