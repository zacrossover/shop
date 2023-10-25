package com.example.shop;

import com.example.shop.dao.UsersDao;
import com.example.shop.entity.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private UsersDao usersDao;
    @Test
    void contextLoads() {
        Users user = usersDao.getById("3");
        System.out.println(user);
    }

}
