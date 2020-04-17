package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 在mybatis中针对，CRUD一共有四个注解
 * @Select @Insert @Update @Delete
 */
@CacheNamespace(blocking = true)
public interface IUserDao  {
    /**
     * 查询所有用户信息
     * @return
     */
    @Select("SELECT * FROM USER")
    @Results(id = "userMap" , value = {
            @Result(id = true,column = "id",property = "userId"),
            @Result(column = "username",property = "userName"),
            @Result(column = "address",property = "userAddress"),
            @Result(column = "sex",property = "userSex"),
            @Result(column = "birthday",property = "userBirthday"),
            @Result(column =  "id",property = "accounts" ,
                    many = @Many(select = "com.itheima.dao.IAccountDao.findAccountByUid",
                    fetchType = FetchType.LAZY))
            }
    )
    List<User> findAll();

    /**
     * 通过id查询用户信息
     * @param userId
     * @return
     */
    @Select("select * from user where id = #{id}")
    @ResultMap("userMap")
    User findById(int userId);

    /**
     * 模糊查询
     * @param username
     * @return
     */
    @Select("select * from user where username like #{username}")
    @ResultMap("userMap")
    List<User> findByName(String username);
}
