package com.example.service.impl;

import com.example.dao.UserDao;
import com.example.dao.impl.UserDaoImpl;
import com.example.domain.PageBean;
import com.example.domain.User;
import com.example.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
UserService接口的实现类
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public List<User> findAll() {  //调用dao层的操作
        return userDao.findAll();
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void deleteUser(String id) {
        //将id转化为int
        userDao.deleteUser(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public User findUserById(String id) {
        return userDao.findUserById(Integer.parseInt(id));
    }

    @Override
    public User login(User user) {
        return userDao.login(user);
    }

    @Override
    public void deleteUsers(String[] ids) {
        //调用UserDao的deleteUser方法
        if (ids!=null && ids.length!=0){
            for (String id: ids){
                userDao.deleteUser(Integer.parseInt(id));
            }
        }
    }

    @Override
    public PageBean findUserByPage(String currentPage_s, String rows_s, Map<String, String[]> condition) {
        //1.新建PageBean
        PageBean pb = new PageBean();
        //2.赋值currentPage和rows
        int currentPage = Integer.parseInt(currentPage_s);
        int rows = Integer.parseInt(rows_s);
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        //3.调用UserDao的findTotalCount方法来查询总记录数
        int totalCount = userDao.findTotalCount(condition);
        pb.setTotalCount(totalCount);
        //4.获得totalPage
        pb.setTotalPage(totalCount % rows ==0 ? totalCount / rows: totalCount / rows + 1);
        //5.获得用户list
        List<User> list = new ArrayList();
        list = userDao.findByPage((currentPage - 1) * rows, rows, condition);
        pb.setList(list);
        return pb;
    }

}
