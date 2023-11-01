package com.example.shop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.shop.dao.UserDao;
import com.example.shop.entity.User;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private UserDao userDao;
    @Test
    void contextLoads() {
        User user = userDao.getById(3);
        System.out.println(user);
    }

}
