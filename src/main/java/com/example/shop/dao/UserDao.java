package com.example.shop.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.shop.entity.User;

@Mapper
public interface UserDao {
    @Select("SELECT * FROM shop.t_user WHERE id = #{id}")
    User getById(int id);

    @Select("select * from shop.t_user where username = #{username} and password = #{password}")
    User getUserByName(String username,String password);

    @Select("SELECT * FROM shop.t_user WHERE username = #{username}")
    int getIdByUsername(String username);
}
