package shop;

/**
 * @author wz
 */
public class Book implements Comparable<Book> {
    public String bookName;
    public int score;
    public Book(String bookName, int score) {
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
    public int compareTo(Book o) {
        return score > o.score ? -1 : 1;
    }

}

