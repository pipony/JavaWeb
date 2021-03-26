package com.example.dao.impl;

import com.example.dao.UserDao;
import com.example.domain.User;
import com.example.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @Override
    public void addUser(User user) {
        try {
            String sql = "INSERT INTO userlist (name, gender, age, address, qq, email) VALUES (?, ?, ?, ?, ?, ?)";
            template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        //根据id找记录并删除
        try {
            String sql = "DELETE FROM userlist WHERE id= ?";
            template.update(sql, id);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            String sql = "UPDATE userlist SET name=?, gender=?, age=?, address=?, qq=?, email=? where id=?";
            template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findUserById(int id) {
        try {
            String sql = "SELECT * FROM userlist WHERE id=?";
            User user = template.query(sql, new BeanPropertyRowMapper<User>(User.class), id).get(0);
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User login(User user) {
        //实现功能：将输入的loginUser参数放到数据库中去查询，并最终返回查询的User结果
        //若没查到，返回null
        try {
            String sql = "select * from userlist where username=? and password=?";
            User findUser = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),
                    user.getUsername(), user.getPassword());
            return findUser;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        try {
            //根据condition设置查询sql
            String sql = "SELECT COUNT(*) FROM userlist WHERE 1=1";
            StringBuilder sb = new StringBuilder();
            //存储value
            List list = new ArrayList();
            //获取key集合
            Set<String> keySet = condition.keySet();
            //获取要拼接的sql字符串
            for (String key: keySet){
                //排除currentPage和rows条件
                if (key.equals("currentPage")||key.equals("rows")) continue;
                //判断该key的value是否为空
                String value = condition.get(key)[0];
                if (value!=null && !"".equals(value)){
                    sb.append(" and "+key+" like ?");  //模糊搜索
                    list.add("%"+value+"%");
                }
            }
            sql = sql + sb.toString();
            int count = template.queryForObject(sql, Integer.class, list.toArray());
            return count;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List findByPage(int start, int rows, Map<String, String[]> condition) {
        List list = new ArrayList();
        try {
            //根据condition设置查询sql
            String sql = "SELECT * FROM userlist WHERE 1=1";
            StringBuilder sb = new StringBuilder();
            //存储value
            List values = new ArrayList();
            //获取key集合
            Set<String> keySet = condition.keySet();
            //获取要拼接的sql字符串
            for (String key: keySet){
                //排除currentPage和rows条件
                if (key.equals("currentPage")||key.equals("rows")) continue;
                //判断该key的value是否为空
                String value = condition.get(key)[0];
                if (value!=null && !"".equals(value)){
                    sb.append(" and "+key+" like ?");  //模糊搜索
                    values.add("%"+value+"%");
                }
            }
            sql = sql + sb.toString() + " limit ? , ?";
            values.add(start);
            values.add(rows);
            list = template.query(sql, new BeanPropertyRowMapper<User>(User.class), values.toArray());
            return list;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
