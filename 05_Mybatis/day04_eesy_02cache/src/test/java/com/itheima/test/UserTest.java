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
    private SqlSessionFactory factory;

    @Before
    public void init() throws Exception {
        //1.读取配置文件，生成字节输入流，并抛出异常
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactorySqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(is);
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
     * 测试一级缓存
     */
    @Test
    public void testFirstLevelCache(){
        User user1 = userDao.findById(41);
        System.out.println(user1);

//        sqlSession.close();
//        //再次获取sqlSession对象
//        sqlSession = factory.openSession();
        sqlSession.clearCache();
        userDao = sqlSession.getMapper(IUserDao.class);

        User user2 = this.userDao.findById(41);
        System.out.println(user2);
        System.out.println(user1==user2);
    }
    /**
     * 测试缓存的同步
     */
    @Test
    public void testClearCache(){
        //1.根据id查询用户
        User user1 = userDao.findById(52);
        System.out.println(user1);

        user1.setUsername("cache");
        user1.setAddress("浙江");
        userDao.updateUser(user1);

        User user2 = this.userDao.findById(52);
        System.out.println(user2);
        System.out.println(user1==user2);
    }



}
