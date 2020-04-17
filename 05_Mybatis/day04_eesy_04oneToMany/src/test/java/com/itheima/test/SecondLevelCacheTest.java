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


public class SecondLevelCacheTest {
    private InputStream is;
    private SqlSessionFactory factory;

    @Before
    public void init() throws Exception {
        //1.读取配置文件，生成字节输入流，并抛出异常
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(is);
    }

    @After
    public void destroy() throws Exception {
        is.close();
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @Test
    public void testFindOne() {
        SqlSession sqlSession = factory.openSession();
        IUserDao userDao1 = sqlSession.getMapper(IUserDao.class);
        User user1 = userDao1.findById(58);
        System.out.println(user1);

        sqlSession.close();

        SqlSession sqlSession1 = factory.openSession();
        IUserDao userDao2 = sqlSession1.getMapper(IUserDao.class);
        User user2 = userDao2.findById(58);
        System.out.println(user2);

        sqlSession1.close();


    }


}
