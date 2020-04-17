package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class UserTest {
    private InputStream is;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before
    public void init() throws Exception {
        //1.读取配置文件，生成字节输入流，并抛出异常
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        //3.获取SqlSession对象
        sqlSession = factory.openSession(true);
        //4.获取dao的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws Exception {
        //提交事务
        //sqlSession.commit();
        //6.释放所有资源
        sqlSession.close();
        is.close();
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @Test
    public void testFindAll() throws Exception {
        //5.执行查询所有的方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println("-----每个用户的信息-----");
            System.out.println(user);
            System.out.println(user.getRoles());
        }
    }

}
