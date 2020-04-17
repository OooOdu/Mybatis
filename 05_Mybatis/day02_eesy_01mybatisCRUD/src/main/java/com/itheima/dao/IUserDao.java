package com.itheima.dao;

import com.itheima.domain.QueryVo;
import com.itheima.domain.User;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface IUserDao {
    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();

    /**
     * 保存用户
     */
    void saveUser(User user);

    /**
     * 更新用户
     */
    void updateUser(User user);

    /**
     * 删除用户
     */
    void deleteUser(int id);

    /**
     * 根据id查询用户信息
     */
    User findById(Integer id);

    /**
     * 根据name查询用户信息
     */
    List<User> findByName(String name);

    /**
     * 查询用户总数
     */
    int findTotal();

    /**
     * 根据queryVo的条件查询用户信息
     */
    List<User> findByVo(QueryVo vo);

}
