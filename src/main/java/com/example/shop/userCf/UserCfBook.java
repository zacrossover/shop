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
        // 根据评分进行比较
        if (this.score < o.score) {
            return -1;
        } else if (this.score > o.score) {
            return 1;
        } else {
            return 0;
        }
    }

}

