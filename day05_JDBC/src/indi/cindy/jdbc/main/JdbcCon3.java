package indi.cindy.jdbc.main;

import indi.cindy.jdbc.domain.Emp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
实现ResultSet executeQuery(String sql) ：执行DQL（select)语句
额外定义一个类：indi.cindy.jdbc.domain->Emp，来装查询结果
额外需要释放ResultSet对象
*/

public class JdbcCon3 {
    public void connect(List<Emp> list) throws ClassNotFoundException, SQLException {

        //因为finally无法读到try中新建的变量
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            //1.导入驱动jar包(add as library)
            //2.注册驱动,mysql5之后的驱动jar包可以省略注册驱动的步骤。
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //3.获取数据集连接对象
            //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1?serverTimezone=GMT%2B8", "root", "19961110hxdabcd");
            conn = DriverManager.getConnection("jdbc:mysql:///db1?serverTimezone=GMT%2B8", "root", "19961110hxdabcd"); //本地简写
            //4.定义查询的mysql语句
            String sql = "SELECT * FROM t2";
            //5.获取执行sql的Statement对象
            stmt = conn.createStatement();
            //6.执行sql
            rs = stmt.executeQuery(sql);
            //7.将rs存储到List<Emp>中
            while(rs.next()){
                list.add(new Emp(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
        }
        finally {
            //8.释放三个资源
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

    public List<Emp> findAll() throws SQLException, ClassNotFoundException {
        List<Emp> list = new ArrayList<>();
        connect(list);
        return list;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        List<Emp> list = new JdbcCon3().findAll();
        for(Emp emp: list){
            emp.print();
        }
    }
    
}
