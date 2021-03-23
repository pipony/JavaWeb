package com.example.dao;

import com.example.domain.User;
import com.example.util.JDBCUtils;
import javafx.beans.property.Property;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/*
操作数据库中的User表
 */
public class UserDao {
    //定义一个JDBCTemplate变量，用于这个类中的所有方法公用
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    //定义一个登录方法
    public User login(User loginUser){
        //实现功能：将输入的loginUser参数放到数据库中去查询，并最终返回查询的User结果
        //若没查到，返回null
        try {
            String sql = "select * from usertable where username=? and password=?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUsername(), loginUser.getPassword());
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }


    }
}
