package com.example.shop.dao;

import com.example.shop.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UsersDao {
    @Select("select * from shop.users where id = #{id}")
    Users getById(String id);

}
