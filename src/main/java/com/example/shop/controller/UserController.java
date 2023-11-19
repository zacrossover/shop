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

    @GetMapping("/logIn")
    public Result logIn(String username,String password) {

        return new Result(1, "调用成功", userService.logIn(username,password));
    }

}
