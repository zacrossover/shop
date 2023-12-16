package com.example.shop.service;

import java.util.List;

import com.example.shop.userCf.BookRatings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shop.dao.BookDao;
import com.example.shop.entity.Book;
import com.github.pagehelper.PageHelper;

import io.micrometer.common.util.StringUtils;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookDao bookDao;
	
	public void fillPicture(List<Book> books) {
		StringBuilder sb;
		for(Book b : books) {
			if(StringUtils.isBlank(b.getPictures())) {
				continue;
			}
			sb = new StringBuilder();
			for(String p : b.getPictures().split(",")) {
				sb.append("/image/").append(b.getClassification()).append("/").append(p).append(",");
			}
			b.setPictures(sb.substring(0, sb.length() - 1));
		}
	}
	
	@Override
    public List<Book> list(int pageNum, int pageSize, Integer status) {
		PageHelper.startPage(pageNum, pageSize);
		List<Book> books = bookDao.list(status);
		fillPicture(books);
    	return books;
    }
	
	@Override
	public List<Book> listByName(String bookName, Integer status) {
		List<Book> books = bookDao.listByName(bookName, status);
		fillPicture(books);
		return books;
	}
	
	@Override
	public List<Book> listByClassification(String classification, Integer status) {
		List<Book> books = bookDao.listByClassification(classification, status);
		fillPicture(books);
		return books;
	}

	@Override
	public Book getById(int id) {
		Book b = bookDao.getById(id);
		if(StringUtils.isNotBlank(b.getPictures())) {
			StringBuilder sb;
			sb = new StringBuilder();
			for(String p : b.getPictures().split(",")) {
				sb.append("/image/").append(b.getClassification()).append("/").append(p).append(",");
			}
			b.setPictures(sb.substring(0, sb.length() - 1));
		}
		System.out.println(b.getAvgScore());
		return b;
	}

	@Override
	public int add(Book book) {
		return bookDao.add(book);
	}

	@Override
	public int update(Book book) {
		return bookDao.update(book);
	}
	
	@Override
	public int updateStatusById(int id, int status) {
		return bookDao.updateStatusById(id, status);
	}

	@Override
	public int delete(int id) {
		return bookDao.delete(id);
	}
}
