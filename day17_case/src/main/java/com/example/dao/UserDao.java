package com.example.dao;
import com.example.domain.User;
import com.example.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/*
dao层的UserDao接口
 */
public interface UserDao {

    //获取所有用户信息
    public List<User> findAll();
}
