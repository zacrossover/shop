package com.example.shop.controller;


import com.example.shop.dto.Result;
import com.example.shop.service.OrderService;
import com.example.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/authentication")
    public Result checkUser(String username,String password) {

        return new Result(1, "查询成功", userService.checkUser(username,password));
    }

}