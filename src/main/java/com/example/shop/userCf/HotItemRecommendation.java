package com.example.shop.userCf;
import com.example.shop.userCf.UserCfBook;
import java.util.*;

public class HotItemRecommendation {
    // 定义热门物品列表
    private static List<UserCfBook> popularItems = new ArrayList<>();

    public static List<UserCfBook> recommendHotItems(List<UserCfBook> recommendationBooks,String userId,int number) {
        //计算每本书的平均评分。
        BookRatings.calculateAverageRatings();
        Map<String, Map<String, Integer>> bookRatingsMap = BookRatings.getBookRatings();

        for (Map.Entry<String, Map<String, Integer>> entry : bookRatingsMap.entrySet()) {
            String bookName = entry.getKey();
            Map<String, Integer> ratings = entry.getValue();
            int averageRating = ratings.getOrDefault("averageRating", 0);
            //System.out.println("图书：" + bookName + "，平均评分：" + averageRating);
            boolean ok = false;
            for (UserCfBook book:popularItems) {
                if (book.bookName.equals(bookName)) {
                    book.score = averageRating;
                    ok = true;
                }
            }
            if(!ok)
            {
                if(averageRating>0) {
                    popularItems.add(new UserCfBook(bookName, averageRating));
                }
            }
        }
        Collections.sort(popularItems);

        List<UserCfBook> books= new ArrayList<>();
        int index = 0;
        while(number>0)
        {
            boolean ok = false;
            for(UserCfBook b:recommendationBooks)
            {
                if (b.bookName.equals(popularItems.get(index).bookName))
                {
                    ok = true;
                    break;
                }
            }
            if(ok == false)
            {
                books.add(popularItems.get(index));
                number-=1;
            }
            index +=1;
        }
        // 给新用户推荐热门物品
        return books; // 推荐前5个热门物品
    }

}
