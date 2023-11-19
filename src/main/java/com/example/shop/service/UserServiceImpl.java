package com.example.shop.service;

import com.example.shop.dao.OrderDao;
import com.example.shop.dao.UserDao;
import com.example.shop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    public Map<String,String> logIn(String username,String password){
        User user = userDao.getUserByName(username,password);
        int roleId = 0;
        int userId = 0;
        String resultname = null;
        Map<String,String> result = new HashMap<String,String>();
        if(user!=null) {
            roleId = user.getRole();
            userId = user.getId();
            resultname = username;
        }
        result.put("username",resultname);
        result.put("userId",String.valueOf(userId));
        result.put("roleId",String.valueOf(roleId));

        return result;
    }
}
