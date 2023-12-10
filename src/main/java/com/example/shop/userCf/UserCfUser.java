package com.example.shop.userCf;

import java.util.ArrayList;
import java.util.List;


/**
 * @author wz
 */
public class UserCfUser {
    public String username;
    public List<UserCfBook> bookList = new ArrayList<>();

    public UserCfUser() {}

    public UserCfUser(String username) {
        this.username = username;
    }

    public UserCfUser set(String bookName, int score) {
        if(find(bookName)==null)
        {
            this.bookList.add(new UserCfBook(bookName, score));
        }
        return this;
    }

    public UserCfBook find(String bookName) {
        for (UserCfBook book : bookList) {
            if (book.bookName.equals(bookName)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }
}

