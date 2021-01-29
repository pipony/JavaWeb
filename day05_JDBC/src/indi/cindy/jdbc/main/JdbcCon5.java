package indi.cindy.jdbc.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*
实现JDBC的事务控制: commit and rollback
一个事务中的操作：要么都实现，要么都不实现
利用try...catch
 */
public class JdbcCon5 {
    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        try {
            //1.获取连接
            conn = JdbcUtils.getConnection();
            //2.开启事务
            conn.setAutoCommit(false);
            //3.sql语句并执行
            //实现功能：分别给两个用户的年龄减5和加5岁，这两个算作一个事务
            String sql1 = "UPDATE t2 SET age = age - ? WHERE number = ?";
            String sql2 = "UPDATE t2 SET age = age + ? WHERE number = ?";
            pstmt1 = conn.prepareStatement(sql1);
            pstmt2 = conn.prepareStatement(sql2);
            pstmt1.setInt(1, 5);
            pstmt1.setInt(2, 1);
            pstmt2.setInt(1, 5);
            pstmt2.setInt(2, 2);
            //4.执行第一个sql
            pstmt1.executeUpdate();
            //5.中间插一个异常事件
            int temp = 5 / 0;
            //6.执行第二个sql
            pstmt2.executeUpdate();
            //7.最后提交事务
            conn.commit(); //注意是conn来提交
        } catch (Exception e){
            //出现异常，回滚
            if(conn!=null){
                conn.rollback();
            }
            e.printStackTrace();
        } finally {
            //释放资源
            JdbcUtils.close(pstmt1, conn);
            JdbcUtils.close(pstmt2, null);
        }
    }
}
