package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {
    private InputStream is;
    private IUserDao userDao;

    @Before
    public void init() throws Exception {
        //1.读取配置文件，生成字节输入流，并抛出异常
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        //3.使用工厂对象创建dao对象
        userDao = new UserDaoImpl(factory);
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
    public void testFindAll() throws Exception {
        //5.执行查询所有的方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 保存用户
     */
    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUsername("dao_impl_user");
        user.setAddress("浙江绍兴");
        user.setSex("男");
        user.setBirthday(new Date());
        System.out.println("保存操作之前：" + user);
        userDao.saveUser(user);
        System.out.println("保存操作之后：" + user);
    }

    /**
     * 更新用户
     */
    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId(52);
        user.setUsername("mybatis_update_user");
        user.setAddress("浙江绍兴");
        user.setSex("男");
        user.setBirthday(new Date());
        userDao.updateUser(user);
    }

    /**
     * 删除用户
     */
    @Test
    public void testDeleteUser() {
        userDao.deleteUser(57);
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
     * 查询寻用户总数
     */
    @Test
    public void testTotal() {
        int count = userDao.findTotal();
        System.out.println(count);
    }

}
