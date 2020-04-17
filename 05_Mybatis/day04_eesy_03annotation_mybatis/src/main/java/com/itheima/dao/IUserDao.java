package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 在mybatis中针对，CRUD一共有四个注解
 * @Select @Insert @Update @Delete
 */
public interface IUserDao  {
    /**
     * 查询所有用户信息
     * @return
     */
    @Select("SELECT * FROM USER")
    List<User> findAll();

    /**
     * 保存用户
     * @param user
     */
    @Insert("insert into user(username,address,sex,birthday) values(#{username},#{address},#{sex},#{birthday})")
    void saveUser(User user);

    /**
     * 更新用户
     * @param user
     */
    @Update("update user set username=#{username},address=#{address},sex=#{sex},birthday=#{birthday} where id=#{id}")
    void updateUser(User user);

    /**
     * 删除用户信息
     * @param id
     */
    @Delete("delete from user where id = #{id} ")
    void deleteUser(Integer id);
}
