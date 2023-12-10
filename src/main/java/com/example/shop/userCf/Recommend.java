package com.example.shop.userCf;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.min;

/**
 * @author wz
 */
public class Recommend {
    /**
     * 在给定username的情况下，计算其他用户和它的距离并排序
     * @param username
     * @return
     */
    private Map<Double, String> computeNearestNeighbor(String username, List<UserCfUser> users) {
        Map<Double, String> distances = new TreeMap<>();

        UserCfUser u1 = new UserCfUser(username);
        for (UserCfUser user:users) {
            if (username.equals(user.username)) {
                u1 = user;
            }
        }

        for (int i = 0; i < users.size(); i++) {
            UserCfUser u2 = users.get(i);

            if (!u2.username.equals(username)) {
                double distance = pearson_dis(u2.bookList, u1.bookList);
                distances.put(distance, u2.username);
            }

        }
        System.out.println("该用户与其他用户的皮尔森相关系数 -> " + distances);
        return distances;
    }


    /**
     * 计算2个打分序列间的pearson距离
     * 选择公式四进行计算
     * @param rating1
     * @param rating2
     * @return
     */
    private double pearson_dis(List<UserCfBook> rating1, List<UserCfBook> rating2) {
        int n=min(rating1.size(),rating2.size());
        List<Integer> rating1ScoreCollect = rating1.stream().map(A -> A.score).collect(Collectors.toList());
        List<Integer> rating2ScoreCollect = rating2.stream().map(A -> A.score).collect(Collectors.toList());

        double Ex= rating1ScoreCollect.stream().mapToDouble(x->x).sum();
        double Ey= rating2ScoreCollect.stream().mapToDouble(y->y).sum();
        double Ex2=rating1ScoreCollect.stream().mapToDouble(x->Math.pow(x,2)).sum();
        double Ey2=rating2ScoreCollect.stream().mapToDouble(y->Math.pow(y,2)).sum();
        double Exy= IntStream.range(0,n).mapToDouble(i->rating1ScoreCollect.get(i)*rating2ScoreCollect.get(i)).sum();
        double numerator=Exy-Ex*Ey/n;
        double denominator=Math.sqrt((Ex2-Math.pow(Ex,2)/n)*(Ey2-Math.pow(Ey,2)/n));
        if (denominator==0) return 0.0;
        return numerator/denominator;
    }


    public List<UserCfBook> recommend(String username, List<UserCfUser> users , int number) {


        UserCfUser userRatings = new UserCfUser();
        for (UserCfUser user:users) {
            if (username.equals(user.username)) {
                userRatings = user;
            }
        }
        System.out.println("用户看过的电影 -> " + userRatings.bookList);

        List<UserCfBook> recommendationBooks = new ArrayList<>();
        //找到最近邻
        Map<Double, String> distances = computeNearestNeighbor(username, users);
        //判断用户是否为新用户
        if(userRatings.bookList.isEmpty())
        {
            recommendationBooks =  HotItemRecommendation.recommendHotItems(recommendationBooks,username,number);
        }else
        {
            for (String value : distances.values()) {
                System.out.println("最近邻 -> " + value);

                //找到最近邻看过，但是我们没看过的电影，计算推荐
                UserCfUser neighborRatings = new UserCfUser();
                for (UserCfUser user : users) {
                    if (value.equals(user.username)) {
                        neighborRatings = user;
                    }
                }
                System.out.println("最近邻看过的电影 -> " + neighborRatings.bookList);
                //根据自己和邻居的电影计算推荐的电影
                for (UserCfBook book : neighborRatings.bookList) {
                    if (userRatings.find(book.bookName) == null) {
                        recommendationBooks.add(book);
                        if(recommendationBooks.size()>=number)
                        {
                            Collections.sort(recommendationBooks);
                            return recommendationBooks;
                        }
                    }
                }

            }
            if(recommendationBooks.size()<number)
            {
                recommendationBooks.addAll( HotItemRecommendation.recommendHotItems(recommendationBooks,username,number-recommendationBooks.size()));
            }

        }
        Collections.sort(recommendationBooks);
        return recommendationBooks;
    }
}

