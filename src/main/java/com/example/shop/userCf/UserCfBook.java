package com.example.shop.userCf;

/**
 * @author wz
 */
public class UserCfBook implements Comparable<UserCfBook> {
    public String bookName;
    public int score;
    public UserCfBook(String bookName, int score) {
        this.bookName = bookName;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", score=" + score +
                '}';
    }

    @Override
    public int compareTo(UserCfBook o) {
        return score > o.score ? -1 : 1;
    }

}

