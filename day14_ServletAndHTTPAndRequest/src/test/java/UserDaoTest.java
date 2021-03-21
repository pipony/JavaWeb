import com.example.dao.UserDao;
import com.example.domain.User;
import org.junit.Test;

/*
测试UserDao
 */
public class UserDaoTest {

    @Test
    public void testLogin(){
        //新建一个User类来测试
        User user = new User();
        user.setUsername("cindy");
        user.setPassword("1996");
        User findUser = new UserDao().login(user);
        System.out.println(findUser);

    }

}
