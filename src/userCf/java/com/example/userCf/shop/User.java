package shop;

import java.util.ArrayList;
import java.util.List;


/**
 * @author wz
 */
public class User {
    public String username;
    public List<Book> bookList = new ArrayList<>();

    public User() {}

    public User(String username) {
        this.username = username;
    }

    public User set(String bookName, int score) {
        this.bookList.add(new Book(bookName, score));
        BookRatings.addRating(bookName, score);
        return this;
    }

    public Book find(String bookName) {
        for (Book book : bookList) {
            if (book.bookName.equals(username)) {
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

