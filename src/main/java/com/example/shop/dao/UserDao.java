package com.example.shop.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.shop.entity.User;

@Mapper
public interface UserDao {
    @Select("SELECT * FROM shop.t_user WHERE id = #{id}")
    User getById(int id);

}