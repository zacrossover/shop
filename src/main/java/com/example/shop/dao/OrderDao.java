package com.example.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.shop.entity.Order;

@Mapper
public interface OrderDao {
	/**
	 * 新增订单
	 * @param order
	 * @return
	 */
	@Insert("INSERT INTO t_order(order_no, user_id, book_id, book_name, classification, price, quantity, description, pictures, status, create_time) VALUES(#{orderNo}, #{userId}, #{bookId}, #{bookName}, #{classification}, #{price}, #{quantity}, #{description}, #{pictures}, #{status}, CURRENT_TIMESTAMP())")
	int add(Order order);
	
	/**
	 * 按userId查询订单
	 * @return
	 */
	@Select("SELECT * FROM t_order WHERE user_id = #{userId}")
	List<Order> listByUserId(int userId);
	
	/**
	 * 按id查询订单
	 * @param id
	 * @return
	 */
    @Select("SELECT * FROM t_order WHERE id = #{id}")
    Order getById(int id);

}
