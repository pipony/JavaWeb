package indi.cindy.jdbc.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
最基本的jdbc连接方式
 */

public class JdbcCon1 {
    public void connect() throws ClassNotFoundException, SQLException {
        //1.导入驱动jar包(add as library)
        //2.注册驱动,mysql5之后的驱动jar包可以省略注册驱动的步骤。
        //Class.forName("com.mysql.cj.jdbc.Driver");
        //3.获取数据集连接对象
        //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1?serverTimezone=GMT%2B8", "root", "19961110hxdabcd");
        Connection conn = DriverManager.getConnection("jdbc:mysql:///db1?serverTimezone=GMT%2B8", "root", "19961110hxdabcd"); //本地简写
        //4.定义查询的mysql语句
        String sql = "INSERT INTO t1 VALUES(50)";
        //5.获取执行sql的Statement对象
        Statement stmt = conn.createStatement();
        //6.执行sql
        int count = stmt.executeUpdate(sql);
        //7.输出执行的结果
        System.out.println(count);
        //8.释放两个资源
        stmt.close();
        conn.close();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new JdbcCon1().connect();
    }
    
}
