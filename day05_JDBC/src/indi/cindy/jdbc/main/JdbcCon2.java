package indi.cindy.jdbc.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
进阶的JdbcCon1:考虑当程序中途中断，无法进行最后两行的释放stmt和conn资源。于是用try，finally来保证资源一定释放
*/

public class JdbcCon2 {
    public void connect() throws ClassNotFoundException, SQLException {

        //因为finally无法读到try中新建的变量
        Connection conn = null;
        Statement stmt = null;

        try{
            //1.导入驱动jar包(add as library)
            //2.注册驱动,mysql5之后的驱动jar包可以省略注册驱动的步骤。
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //3.获取数据集连接对象
            //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1?serverTimezone=GMT%2B8", "root", "19961110hxdabcd");
            conn = DriverManager.getConnection("jdbc:mysql:///db1?serverTimezone=GMT%2B8", "root", "19961110hxdabcd"); //本地简写
            //4.定义查询的mysql语句
            String sql = "INSERT INTO t1 VALUES(50)";
            //5.获取执行sql的Statement对象
            stmt = conn.createStatement();
            //6.执行sql
            int count = stmt.executeUpdate(sql);
            //7.输出执行的结果
            System.out.println(count);
        }
        finally {
            //8.释放两个资源
            //释放第一个
            if(stmt!=null){
                try {
                    stmt.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
            //释放第二个
            if(conn!=null){
                try {
                    conn.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new JdbcCon2().connect();
    }
    
}
