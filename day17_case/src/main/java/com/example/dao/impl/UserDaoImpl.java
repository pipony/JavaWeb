package com.example.dao.impl;

import com.example.dao.UserDao;
import com.example.domain.User;
import com.example.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/*
UserDao接口的实现类
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<User> findAll() {
        //使用jdbc操作数据库返回List<User>
        try {
            String sql = "SELECT * FROM userlist";
            List<User> list = new ArrayList<>();
            list = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
            return list;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
