package com.example.shop.service;

import java.util.*;

import com.example.shop.userCf.BookRatings;
import com.example.shop.userCf.Recommend;
import com.example.shop.userCf.UserCfBook;
import com.example.shop.userCf.UserCfUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shop.dao.OrderDao;
import com.example.shop.dao.BookDao;
import com.example.shop.entity.Book;
import com.example.shop.entity.Order;
import com.github.pagehelper.PageHelper;
@Service
public class RecommandServiceImpl implements RecommandService{


    private static Map<String,UserCfUser> users = new Map<String, UserCfUser>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean containsKey(Object key) {
            return false;
        }

        @Override
        public boolean containsValue(Object value) {
            return false;
        }

        @Override
        public UserCfUser get(Object key) {
            return null;
        }

        @Override
        public UserCfUser put(String key, UserCfUser value) {
            return null;
        }

        @Override
        public UserCfUser remove(Object key) {
            return null;
        }

        @Override
        public void putAll(Map<? extends String, ? extends UserCfUser> m) {

        }

        @Override
        public void clear() {

        }

        @Override
        public Set<String> keySet() {
            return null;
        }

        @Override
        public Collection<UserCfUser> values() {
            return null;
        }

        @Override
        public Set<Entry<String, UserCfUser>> entrySet() {
            return null;
        }
    };
    @Autowired
    private BookDao bookDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserService userService;
    public void fillPicture(List<Book> books) {
        StringBuilder sb;
        for(Book b : books) {
            sb = new StringBuilder();
            for(String p : b.getPictures().split(",")) {
                sb.append("/image/").append(b.getClassification()).append("/").append(p).append(",");
            }
            b.setPictures(sb.substring(0, sb.length() - 1));
        }
    }
    @Override
    public Object recommandBooks(int myUserId, int nums) {
        List<Order> orders = orderDao.list();
        for(Order o :orders)
        {
            String username = o.getUsername();
            int userId = userService.getIdByUsername(username);
            int bookId = o.getBookId();
            int score = o.getAvgScore();
            if(users.containsKey(Integer.toString(userId)))
            {
                users.get(Integer.toString(userId)).set(Integer.toString(bookId), score);
            }else {

                users.put(Integer.toString(userId),new UserCfUser(Integer.toString(userId))
                        .set(Integer.toString(bookId), score));
            }
        }
        List<UserCfUser> userList = new ArrayList<>();

        for (Map.Entry<String, UserCfUser> entry : users.entrySet()) {
            userList.add(entry.getValue());

        }
        Recommend recommend = new Recommend();
        List<UserCfBook> recommendationBooks = recommend.recommend(Integer.toString(myUserId), userList,nums );
        List<Book> books = new ArrayList<>();
        for(UserCfBook userCfBook :recommendationBooks)
        {
            Book book = bookDao.getById(Integer.parseInt(userCfBook.bookName));
            books.add(book);
        }
        fillPicture(books);
        return books;
    }
}
