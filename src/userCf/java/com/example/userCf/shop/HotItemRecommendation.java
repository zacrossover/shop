package shop;

import java.util.*;

public class HotItemRecommendation {
    // 定义热门物品列表
    private static List<Book> popularItems = new ArrayList<>();

    public static List<Book> recommendHotItems(String userId,int number) {
        //计算每本书的平均评分。
        BookRatings.calculateAverageRatings();
        Map<String, Map<String, Integer>> bookRatingsMap = BookRatings.getBookRatings();

        for (Map.Entry<String, Map<String, Integer>> entry : bookRatingsMap.entrySet()) {
            String bookName = entry.getKey();
            Map<String, Integer> ratings = entry.getValue();
            int averageRating = ratings.getOrDefault("averageRating", 0);
            System.out.println("图书：" + bookName + "，平均评分：" + averageRating);
            boolean ok = false;
            for (Book book:popularItems) {
                if (book.bookName.equals(bookName)) {
                    book.score = averageRating;
                    ok = true;
                }
            }
            if(!ok)
            {
                popularItems.add(new Book(bookName,averageRating));
            }
        }
        Collections.sort(popularItems);
        // 给新用户推荐热门物品
        return popularItems.subList(0, Math.min(number, popularItems.size())); // 推荐前5个热门物品
    }

}
