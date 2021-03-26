package com.example.dao;
import com.example.domain.User;
import com.example.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/*
dao层的UserDao接口
 */
public interface UserDao {

    //获取所有用户信息
    public List<User> findAll();
    //添加新用户信息
    public void addUser(User user);
    //删除用户
    public void deleteUser(int id);
    //更新用户
    public void updateUser(User user);
    //根据id查找对应用户
    public User findUserById(int id);
    //登录验证
    public User login(User user);
    //查找总记录数
    public int findTotalCount(Map<String, String[]> condition);
    //分页查询List
    public List findByPage(int start, int rows, Map<String, String[]> condition);
}
