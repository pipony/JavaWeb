package com.example.service.impl;

import com.example.dao.UserDao;
import com.example.dao.impl.UserDaoImpl;
import com.example.domain.User;
import com.example.service.UserService;

import java.util.List;

/*
UserService接口的实现类
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public List<User> findAll() {  //调用dao层的操作
        return userDao.findAll();
    }
}
