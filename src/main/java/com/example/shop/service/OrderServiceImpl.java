package com.example.shop.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shop.dao.OrderDao;
import com.example.shop.entity.Book;
import com.example.shop.entity.Order;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;

	@Override
	public int add(int userId, Book book) {
		Order order = new Order();
		order.setOrderNo(UUID.randomUUID().toString().replace("-", ""));
		order.setUserId(userId);
		order.setBookId(book.getId());
/*		order.setBookName(book.getBookName());
		order.setClassification(book.getClassification());
		order.setPrice(book.getPrice());
		order.setDescription(book.getDescription());
		order.setPictures(book.getPictures());*/
		order.setStatus(2);
		return orderDao.add(order);
	}

	@Override
	public List<Order> listByUserId(int userId) {
		return orderDao.listByUserId(userId);
	}

	@Override
	public Order getById(int id) {
		return orderDao.getById(id);
	}
	
}
