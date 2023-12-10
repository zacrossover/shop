package com.example.shop.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import com.example.shop.userCf.BookRatings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.shop.dto.Result;
import com.example.shop.entity.Book;
import com.example.shop.service.BookService;
import com.example.shop.service.OrderService;
import com.example.shop.util.ImagePathUtil;

import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/book")
public class BookController {
	final static Logger logger = LoggerFactory.getLogger(BookController.class);
	final static int DEFAULT_BOOK_QUANTITY = 100;
	
	@Autowired
	private BookService bookService;
	@Autowired
	private OrderService orderService;
	
	/**
	 * 查询上架图书
	 * @return
	 */
    @GetMapping("/list")
    public Result list(int pageNum, int pageSize, Integer status) {
        return new Result(1, "查询成功", bookService.list(pageNum, pageSize, status));
    }
    
	/**
     * 按名称查询图书
     * @param bookName
     * @return
     */
    @GetMapping("/listByName")
	public Result listByName(@NotBlank String bookName, Integer status) {
		return new Result(1, "查询成功", bookService.listByName(bookName, status));
	}
    
	/**
     * 按分类查询图书
     * @param classification
     * @return
     */
    @GetMapping("/listByClassification")
    public Result listByClassification(@NotBlank String classification, Integer status) {
		return new Result(1, "查询成功", bookService.listByClassification(classification, status));
	}

	/**
     * 按id查询图书
     * @param id
     * @return
     */
    @GetMapping("/getById")
	public Result getById(int id) {
		return new Result(1, "查询成功", bookService.getById(id));
	}

	/**
     * 添加图书
     * @param book
     * @return
     */
    @PostMapping("/add")
	public Result add(@Validated Book book, MultipartFile file) {
    	if(file == null) {
    		return new Result(0, "图片不能为空");
    	}
    	try {
    		//上传图片至'resources\{图书分类}\'目录下
    		String path = ImagePathUtil.getImagePath() + book.getClassification() + System.getProperty("file.separator");
    		File dir = new File(path);
    		if(!dir.exists()) {
    			dir.mkdir();
    		}
			file.transferTo(new File(path + file.getOriginalFilename()));
			book.setPictures(file.getOriginalFilename());
			book.setBookNo(UUID.randomUUID().toString().replace("-", ""));
			if(book.getQuantity() == 0) {
				book.setQuantity(DEFAULT_BOOK_QUANTITY);
			}
			book.setStatus(1);
			bookService.add(book);
		} catch (IllegalStateException | IOException e) {
			logger.error("添加图书失败", e);
			return new Result(0, "添加失败");
		}
		BookRatings.addRating(Integer.toString(book.getId()), (int) Math.round(book.getAvg_Score()));
		return new Result(1, "添加成功");
	}

	/**
     * 修改图书
     * @param book
     * @return
     */
    @PostMapping("/update")
	public Result update(@Validated Book book, MultipartFile file) {
    	if(file != null) {
    		//上传图片至'resources\{图书分类}\'目录下
    		//Book b = bookService.getById(book.getId());
    		String path = ImagePathUtil.getImagePath() + book.getClassification() + System.getProperty("file.separator");
    		File dir = new File(path);
    		if(!dir.exists()) {
    			dir.mkdir();
    		}
    		try {
    			file.transferTo(new File(path + file.getOriginalFilename()));
    		} catch (IllegalStateException | IOException e) {
    			logger.error("修改图书失败", e);
    			return new Result(0, "修改失败");
    		}
    		book.setPictures(file.getOriginalFilename());
    	}
		bookService.update(book);
		/*
		 * 删除后影响历史订单图片的展示
		 * for(String p : b.getPictures().split(",")) { File f = new
		 * File(ImagePathUtil.getImagePath() + b.getClassification() +
		 * System.getProperty("file.separator") + p); f.delete();//删除旧图片 }
		 */
		return new Result(1, "更新成功");
	}
	
    /**
     * 图书上/下架
     * @param id
     * @param status 1-上架，0-下架
     * @return
     */
    @PostMapping("/updateStatusById")
	public Result updateStatusById(int id, int status) {
		bookService.updateStatusById(id, status);
		return new Result(1, "操作成功");
	}

	/**
     * 删除图书
     * @param id
     * @return
     */
    @PostMapping("/delete")
	public Result delete(int id) {
		/*
		 * Book b = bookService.getById(id); for(String p : b.getPictures().split(","))
		 * { File f = new File(ImagePathUtil.getImagePath() + b.getClassification() +
		 * System.getProperty("file.separator") + p); f.delete();//删除旧图片 }
		 */
		bookService.delete(id);
		BookRatings.deleteRating(Integer.toString(id));
		return new Result(1, "操作成功");
	}
    
	/**
     * 购买图书
     * @param bookId
     * @param username
     * @return
     */
    @PostMapping("/buy")
	public Result buy(int bookId, String username) {
    	Book book = bookService.getById(bookId);
		orderService.add(username, book);
		return new Result(1, "购买成功");
	}

}
