package shop;

import java.util.HashMap;
import java.util.Map;

public class BookRatings {
    private static Map<String, Map<String, Integer>> bookRatings = new HashMap<>();

    public static void addRating(String bookName, int rating) {
        if (bookRatings.containsKey(bookName)) {
            Map<String, Integer> ratings = bookRatings.get(bookName);
            int sum = ratings.getOrDefault("sum", 0);
            int count = ratings.getOrDefault("count", 0);

            ratings.put("sum", sum + rating);
            ratings.put("count", count + 1);
        } else {
            Map<String, Integer> ratings = new HashMap<>();
            ratings.put("sum", rating);
            ratings.put("count", 1);
            bookRatings.put(bookName, ratings);
        }
    }

    public static void calculateAverageRatings() {
        for (Map.Entry<String, Map<String, Integer>> entry : bookRatings.entrySet()) {
            String bookName = entry.getKey();
            Map<String, Integer> ratings = entry.getValue();

            int sum = ratings.getOrDefault("sum", 0);
            int count = ratings.getOrDefault("count", 0);

            double averageRating = count > 0 ? (double) sum / count : 0.0;

            ratings.put("averageRating", (int) averageRating);
        }
    }

    public static Map<String, Map<String, Integer>> getBookRatings() {
        return bookRatings;
    }
}
