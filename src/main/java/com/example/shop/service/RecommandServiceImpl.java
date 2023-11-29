package com.example.shop.service;

import java.util.ArrayList;
import java.util.List;

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
    private static List<UserCfUser> users = new ArrayList<>();
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
            users.add(new UserCfUser(Integer.toString(userId))
                    .set(Integer.toString(bookId), score));
        }
        Recommend recommend = new Recommend();
        List<UserCfBook> recommendationBooks = recommend.recommend(Integer.toString(myUserId), users,nums );
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
