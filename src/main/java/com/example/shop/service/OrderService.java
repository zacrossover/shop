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
	int add(String username, Book book);
	
	/**
	 * 按username查询订单
	 * @return
	 */
	List<Order> listByUsername(String username);
	
	/**
	 * 按id查询订单
	 * @param id
	 * @return
	 */
    Order getById(int id);


	void saveScore(int score1,int score2,int score3,int score4,int orderId);

	float getAverageScore(int bookId,float score);

}
