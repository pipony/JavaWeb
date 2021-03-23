package com.example.service;

import com.example.domain.User;

import java.util.List;

/*
service层中的UserService接口
 */
public interface UserService {
    //获取所有用户信息
    public List<User> findAll();
}
