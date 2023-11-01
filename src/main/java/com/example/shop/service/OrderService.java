package com.example.shop.service;

import java.util.List;

import com.example.shop.entity.Book;
import com.example.shop.entity.Order;

public interface OrderService {
	/**
	 * 新增订单
	 * @param userId
	 * @param book
	 * @return
	 */
	int add(int userId, Book book);
	
	/**
	 * 按userId查询订单
	 * @return
	 */
	List<Order> listByUserId(int userId);
	
	/**
	 * 按id查询订单
	 * @param id
	 * @return
	 */
    Order getById(int id);

}
