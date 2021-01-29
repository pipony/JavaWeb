package indi.cindy.jdbc.main;

import com.sun.corba.se.spi.orbutil.threadpool.NoSuchThreadPoolException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*
实现一个简单的案例：验证用户登录（输入用户名和密码）
提前建好数据集
假设数据集已有(cindy,1996)的记录
 */
public class JdbcTest {
    public static void main(String[] args) throws SQLException {
        //1.输入用户名和密码
        String user;
        String pswd;
        Scanner sc = new Scanner(System.in);
        System.out.print("please enter your username: ");
        user = sc.nextLine();
        System.out.print("please enter your password: ");
        pswd = sc.nextLine();
        //2.排除用户名和密码没输入的情况
        if(user.equals("")||pswd.equals("")) {
            System.out.println("Your username or password is empty!");
            return;
        }
        //3.利用JdbcUtils简便数据库连接
        Connection conn = JdbcUtils.getConnection();
        //4.写sql语句以筛选符合条件的记录，并执行
        String sql = "SELECT * FROM usertable WHERE username=? and password=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,user);
        pstmt.setString(2,pswd);
        ResultSet rs = pstmt.executeQuery();
        //5.判断是否有结果
        if(rs.next()) System.out.println("登录成功");
        else System.out.println("用户名或密码错误");
        //6.释放连接
        JdbcUtils.close(pstmt, conn, rs);
    }
}
