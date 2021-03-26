package com.example.service;

import com.example.domain.PageBean;
import com.example.domain.User;

import java.util.List;
import java.util.Map;

/*
service层中的UserService接口
 */
public interface UserService {
    //获取所有用户信息
    public List<User> findAll();
    //添加一个新用户信息
    public void addUser(User user);
    //删除用户
    public void deleteUser(String id);
    //更新用户
    public void updateUser(User user);
    //根据id查找对应用户
    public User findUserById(String id);
    //登录验证
    public User login(User user);
    //删除选中的用户
    public void deleteUsers(String[] ids);
    //分页查询功能
    public PageBean findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
}
