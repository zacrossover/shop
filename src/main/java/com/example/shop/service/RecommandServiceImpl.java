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


    private static Map<String,UserCfUser> users = new HashMap<String, UserCfUser>();





    @Autowired
    private BookDao bookDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserService userService;
    public void fillPicture(List<Book> books) {
        StringBuilder sb;
        for(Book b : books) {
            if(b==null)
            {
                continue;
            }
            sb = new StringBuilder();
            for(String p : b.getPictures().split(",")) {
                sb.append("/image/").append(b.getClassification()).append("/").append(p).append(",");
            }
            b.setPictures(sb.substring(0, sb.length() - 1));
        }
    }
    @Override
    public Object recommandBooks(String myUserId, int nums) {
        List<Order> orders = orderDao.list();
        for(Order o :orders)
        {
            String username = o.getUsername();
            int bookId = o.getBookId();
            int score = o.getAvgScore();
            if(users.containsKey(username))
            {
                users.get(username).set(Integer.toString(bookId), score);
            }else {

                users.put(username,new UserCfUser(username)
                        .set(Integer.toString(bookId), score));
            }
        }
        List<UserCfUser> userList = new ArrayList<>();


        for (Map.Entry<String, UserCfUser> entry : users.entrySet()) {
            userList.add(entry.getValue());
        }
        if(BookRatings.getBookRatings().isEmpty())
        {
            List<Book> allBooks =bookDao.list(1);
            for(Book b:allBooks)
            {
                BookRatings.addRating(Integer.toString(b.getId()), (int) Math.round(b.getAvgScore()));
            }
        }
        Recommend recommend = new Recommend();
        List<UserCfBook> recommendationBooks = recommend.recommend(myUserId, userList,nums );
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
