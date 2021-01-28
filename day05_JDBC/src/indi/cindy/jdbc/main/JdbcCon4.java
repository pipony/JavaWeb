package indi.cindy.jdbc.main;

import indi.cindy.jdbc.domain.Emp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
在JdbcCon3的基础上用PreparedStatement代替Statement，防止sql注入
*/

public class JdbcCon4 {
    public void connect(List<Emp> list) throws ClassNotFoundException, SQLException {

        //因为finally无法读到try中新建的变量
        Connection conn = null;
        PreparedStatement pstmt = null;

        try{
            //1.导入驱动jar包(add as library)
            //2.注册驱动,mysql5之后的驱动jar包可以省略注册驱动的步骤。
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //3.获取数据集连接对象
            //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1?serverTimezone=GMT%2B8", "root", "19961110hxdabcd");
            conn = DriverManager.getConnection("jdbc:mysql:///db1?serverTimezone=GMT%2B8", "root", "19961110hxdabcd"); //本地简写
            //4.定义查询的mysql语句
            String sql = "SELECT * FROM t2 WHERE number = ? and age = ?";  //？是占位符
            //5.获取执行sql的PreparedStatement对象
            pstmt = conn.prepareStatement(sql);
            //6.给？赋值
            pstmt.setInt(1,1);
            pstmt.setInt(2,25);
            //6.执行sql
            ResultSet rs = pstmt.executeQuery();
            //7.将rs存储到List<Emp>中
            while(rs.next()){
                list.add(new Emp(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
        }
        finally {
            //8.释放两个资源
            //释放第一个
            if(pstmt!=null){
                try {
                    pstmt.close();
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

    public List<Emp> findAll() throws SQLException, ClassNotFoundException {
        List<Emp> list = new ArrayList<>();
        connect(list);
        return list;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        List<Emp> list = new JdbcCon4().findAll();
        for(Emp emp: list){
            emp.print();
        }
    }
    
}
