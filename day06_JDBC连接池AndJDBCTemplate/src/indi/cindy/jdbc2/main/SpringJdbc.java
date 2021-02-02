package indi.cindy.jdbc2.main;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
Spring封装下的JDBC实现,获取DataSource是直接基于JdbcUtils来实现
 */
public class SpringJdbc {
    //1.导入jar包
    //2.定义JDBCTemplate对象，因此数据库现在只需一句
    private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());
    //3.执行sql（添加记录，查询一条记录(Map<String,Object>)，查询很多记录（List<Map(String,Object)>以及List<Emp>，查询总记录数））
    //3.1 添加一条记录(update)
    public void fun1(){
        String sql = "INSERT INTO usertable VALUES(?,?,?)";
        int count = template.update(sql, 2, "tom", "1995");
        System.out.println("fun1's count is: "+count);
    }
    //3.2 查询一条记录(queryForMap)
    public void fun2(){
        String sql = "SELECT * FROM usertable WHERE id=?";
        Map<String, Object> map = template.queryForMap(sql, 1);
        System.out.println(map.get("id")+" "+map.get("username")+" "+map.get("password"));
    }
    //3.3 查询一些记录(queryForList)
    public void fun3(){
        String sql = "SELECT * FROM usertable";
        List<Map<String, Object>> list = template.queryForList(sql);
        for(Map<String, Object> map: list){
            System.out.println(map.get("id")+" "+map.get("username")+" "+map.get("password"));
        }
    }
    //3.4 查询一些记录(query->List<Emp>)
    public void fun4(){
        String sql = "SELECT * FROM usertable";
        List<Emp> list = new ArrayList<>();
        list = template.query(sql, new BeanPropertyRowMapper<Emp>(Emp.class));
        for(Emp emp: list) System.out.println(emp.toString());
    }
    //3.5 查询总记录数(queryForObject，将结果封装为对象，一般用于聚合函数)
    public void fun5(){
        String sql = "SELECT COUNT(*) FROM usertable";
        int count = template.queryForObject(sql, Integer.class);
        System.out.println(count);
    }

   //有点不懂。。不需要释放资源吗？？如果需要释放的话，Statement资源在哪。。


    public static void main(String[] args) {
        SpringJdbc sj = new SpringJdbc();
        //sj.fun1();
        //sj.fun2();
        //sj.fun3();
        //sj.fun4();
        sj.fun5();
    }
}
