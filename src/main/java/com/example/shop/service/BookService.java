package com.example.shop.service;

import java.util.List;

import com.example.shop.entity.Book;

public interface BookService {
	/**
	 * 查询上架图书
	 * @return
	 */
    List<Book> list(int pageNum, int pageSize, Integer status);
    
    /**
     * 按名称查询图书
     * @param bookName
     * @return
     */
    List<Book> listByName(String bookName, Integer status);
    
    /**
     * 按分类查询图书
     * @param classification
     * @return
     */
    List<Book> listByClassification(String classification, Integer status);
    
    /**
     * 按id查询图书
     * @param id
     * @return
     */
    Book getById(int id);
    
    /**
     * 添加图书
     * @param book
     * @return
     */
    int add(Book book);
    
    /**
     * 修改图书
     * @param book
     * @return
     */
    int update(Book book);
    
    /**
     * 图书上/下架
     * @param id
     * @param status 1-上架，0-下架
     * @return
     */
    int updateStatusById(int id, int status);
    
    /**
     * 删除图书
     * @param id
     * @return
     */
    int delete(int id);

}
