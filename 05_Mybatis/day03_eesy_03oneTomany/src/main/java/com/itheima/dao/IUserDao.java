package com.itheima.dao;

import com.itheima.domain.User;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface IUserDao {
    /**
     * 查询所有用户，同时获取到用户下所有的账户信息
     * @return
     */
    List<User> findAll();

    /**
     * 根据id查询用户信息
     */
    User findById(Integer id);


}
