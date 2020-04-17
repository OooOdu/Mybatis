package com.itheima.dao.impl;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import com.mysql.cj.Session;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements IUserDao {
    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    public List<User> findAll() {
        //1. 根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //2.调用SqlSession中的方法，实现查询列表
        List<User> users = sqlSession.selectList("com.itheima.dao.IUserDao.findAll");
        //3.释放资源
        sqlSession.close();
        return  users;
    }

    public void saveUser(User user) {
        //1.根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //2.调用SqlSession中的方法，实现保存
        sqlSession.insert("com.itheima.dao.IUserDao.saveUser",user);
        //3.提交事务
        sqlSession.commit();
        //4.释放资源
        sqlSession.close();
    }

    public void updateUser(User user) {
        //1.根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //2.调用SqlSession中的方法，实现更新
        sqlSession.update("com.itheima.dao.IUserDao.updateUser",user);
        //3.提交事务
        sqlSession.commit();
        //4.释放资源
        sqlSession.close();
    }

    public void deleteUser(int id) {
        //1.根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //2.调用SqlSession中的方法，实现更新
        sqlSession.delete("com.itheima.dao.IUserDao.deleteUser",id);
        //3.提交事务
        sqlSession.commit();
        //4.释放资源
        sqlSession.close();
    }

    public User findById(Integer id) {
        //1. 根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //2.调用SqlSession中的方法，实现查询一个
        User user = sqlSession.selectOne("com.itheima.dao.IUserDao.findById",id);
        //3.释放资源
        sqlSession.close();
        return  user;
    }

    public List<User> findByName(String name) {
        //1. 根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //2.调用SqlSession中的方法，实现查询列表
        List<User> users = sqlSession.selectList("com.itheima.dao.IUserDao.findByName",name);
        //3.释放资源
        sqlSession.close();
        return  users;
    }

    public int findTotal() {
        //1. 根据factory获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //2.调用SqlSession中的方法，实现查询列表
        Integer count = sqlSession.selectOne("com.itheima.dao.IUserDao.findTotal");
        //3.释放资源
        sqlSession.close();
        return  count;
    }
}
