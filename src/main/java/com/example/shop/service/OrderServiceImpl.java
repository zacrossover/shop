package com.example.shop.service;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.math.BigDecimal;

import com.example.shop.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shop.dao.OrderDao;
import com.example.shop.entity.Book;
import com.example.shop.entity.Order;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private BookDao bookDao;
	@Override
	public int add(String username, Book book) {
		Order order = new Order();
		order.setOrderNo(UUID.randomUUID().toString().replace("-", ""));
		order.setUsername(username);
		order.setBookId(book.getId());
		order.setBookName(book.getBookName());
		order.setClassification(book.getClassification());
		order.setPrice(book.getPrice());
		order.setDescription(book.getDescription());
		order.setPictures(book.getPictures());
		order.setStatus(2);
		return orderDao.add(order);
	}

	@Override
	public List<Order> listByUsername(String username) {
		return orderDao.listByUsername(username);
	}

	@Override
	public Order getById(int id) {
		return orderDao.getById(id);
	}


	public void saveScore(int score1,int score2,int score3,int score4,int orderId){
		Order order = getById(orderId);
		order.setScore1(score1);
		order.setScore2(score2);
		order.setScore3(score3);
		order.setScore4(score4);
		BigDecimal s1 = new BigDecimal(score1);
		BigDecimal s2 = new BigDecimal(score2);
		BigDecimal s3 = new BigDecimal(score3);
		BigDecimal s4 = new BigDecimal(score4);
		float score = s1.add(s2).add(s3).add(s4).divide(BigDecimal.valueOf(4)).floatValue();
		//System.out.println(score);
		order.setScore(score);
		int bookId = order.getBookId();
		Book book = bookDao.getById(bookId);
		book.setAvg_Score(getAverageScore(bookId,score));
		orderDao.saveScore(order);
	}

	public float getAverageScore(int bookId,float score){
		List<Order> orderlist = orderDao.listByBookId(bookId);
		float result = 0;
		if(orderlist!=null&&orderlist.size()>0) {
			BigDecimal sum = new BigDecimal(0);
			Iterator it = orderlist.iterator();
			while (it.hasNext()) {
				Order tempOrder = (Order) it.next();
				sum.add(new BigDecimal(tempOrder.getScore()));
			}
			sum.add(new BigDecimal(score));
			result = sum.divide(new BigDecimal(orderlist.size())).floatValue();
		}
		return result;
	}
}
