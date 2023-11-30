package com.example.shop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop.dto.Result;
import com.example.shop.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	final static Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 查询用户订单
	 * @return
	 */
    @GetMapping("/listByUsername")
    public Result listByUserId(String username) {
        return new Result(1, "查询成功", orderService.listByUsername(username));
    }

	/**
     * 按id查询订单
     * @param id
     * @return
     */
    @GetMapping("/getById")
	public Result getById(int id) {
		return new Result(1, "查询成功", orderService.getById(id));
	}


	@PostMapping("/scoring")
	public Result scoring(int score1,int score2,int score3,int score4,int orderId){
		orderService.saveScore(score1,score2,score3,score4,orderId);
		return new Result(1, "调用成功");
	}
	@GetMapping("/getAve")
	public Result getAverageScore(int bookId){
		return new Result(1, "查询成功",orderService.getAverageScore(bookId));
	}

}
