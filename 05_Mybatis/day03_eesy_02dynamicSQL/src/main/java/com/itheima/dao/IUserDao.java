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
     * 根据id查询用户信息
     */
    User findById(Integer id);

    /**
     * 根据name查询用户信息
     */
    List<User> findByName(String name);


    /**
     * 根据queryVo的条件查询用户信息
     */
    List<User> findByVo(QueryVo vo);

    /**
     * 根据传入参数条件查询用户信息
     * @param user 查询的条件，有可能有用户名，有可能有性别，有可能有地址，还有可能都有或者都没有
     * @return
     */
    List<User> findByCondition(User user);

    /**
     * 根据queryvo中提供的ids集合，查询用户信息
     * @param vo
     * @return
     */
    List<User> findUserInIds(QueryVo vo);

}
