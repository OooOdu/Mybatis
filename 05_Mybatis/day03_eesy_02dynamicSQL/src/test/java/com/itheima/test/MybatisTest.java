package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.QueryVo;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MybatisTest {
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
            System.out.println(user);
        }
    }

    /**
     * 根据id查询用户信息
     */
    @Test
    public void testFindById() {
        User u = userDao.findById(52);
        System.out.println(u);
    }

    /**
     * 根据name查询用户信息
     */
    @Test
    public void testFindByName() {
        List<User> users = userDao.findByName("%王%");
//        List<User> users = userDao.findByName("王");
        if (!users.isEmpty()) {
            for (User user : users) {
                System.out.println(user);
            }
        } else {
            System.out.println("没有返回的查询值");
        }
    }

    /**
     * 测试使用QueryVo作为查询条件
     */
    @Test
    public void testFindByVo() {
        QueryVo vo = new QueryVo();
        User createUser = new User();
        createUser.setUsername("%王%");
        vo.setUser(createUser);
        List<User> users = userDao.findByVo(vo);
//        List<User> users = userDao.findByName("王");
        if (!users.isEmpty()) {
            for (User user : users) {
                System.out.println(user);
            }
        } else {
            System.out.println("没有返回的查询值");
        }
    }

    /**
     * 根据条件查询用户信息
     */
    @Test
    public void testFindByCondition() {
        User testUser = new User();
        testUser.setUsername("老王");
        testUser.setSex("女");
        List<User> users = userDao.findByCondition(testUser);
        if (!users.isEmpty()) {
            for (User user : users) {
                System.out.println(user);
            }
        } else {
            System.out.println("没有返回的查询值");
        }
    }

    /**
     * 测试foreach标签的使用
     */
    @Test
    public void testFindUserInIds() {
        QueryVo vo = new QueryVo();
        List<Integer> res = new ArrayList<Integer>();
        res.add(41);
        res.add(42);
        res.add(46);
        vo.setIds(res);
        List<User> users = userDao.findUserInIds(vo);
        if (!users.isEmpty()) {
            for (User user : users) {
                System.out.println(user);
            }
        } else {
            System.out.println("没有返回的查询值");
        }
    }


}
