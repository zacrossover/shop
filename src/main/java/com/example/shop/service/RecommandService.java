package com.example.shop.service;
import java.util.List;

import com.example.shop.entity.Book;
public interface RecommandService {

    /**
     * 查询前nums个推荐图书
     * @return
     */
    Object recommandBooks(int myUserId, int nums);
}
