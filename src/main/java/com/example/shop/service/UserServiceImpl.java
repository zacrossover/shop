package com.example.shop.service;

import com.example.shop.dao.OrderDao;
import com.example.shop.dao.UserDao;
import com.example.shop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    public int checkUser(String username,String password){
        User user = userDao.getUserByName(username,password);
        int roleId = 0;
        if(user!=null)
            roleId = user.getRole();
        return roleId;
    }
}
