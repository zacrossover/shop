package com.example.shop;

import com.example.shop.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.shop.dao.UserDao;
import com.example.shop.entity.User;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;
    @Test
    void contextLoads() {
        //int role = userService.logIn("zhanga","123456");
        //System.out.println(role);
    }
}