package com.example.shop.userCf;

import com.example.shop.userCf.Recommend;
import com.example.shop.userCf.UserCfBook;
import com.example.shop.userCf.UserCfUser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wz
 */
public class Test {
    public static void main(String[] args) {
        //输入用户总量
        List<UserCfUser> users = new ArrayList<>();
        users.add(new UserCfUser("小明")
                .set("挪威的森林", 50)
                .set("基督山伯爵", 30)
                .set("教父", 45)
                .set("破碎的四月", 50)
                .set("尘埃落定", 30)
                .set("活着", 45)
                .set("拿破仑全传", 50));

        users.add(new UserCfUser("小红")
                .set("呼啸山庄", 40)
                .set("尘埃落定", 30)
                .set("活着", 50)
                .set("牛虻", 50)
                .set("火星救援", 30)
                .set("拿破仑全传", 30));

        users.add(new UserCfUser("小阳")
                .set("毛概", 20)
                .set("美的历程", 50)
                .set("平凡的世界", 50)
                .set("肖洛特烦恼", 45)
                .set("小鹿斑比", 50));

        users.add(new UserCfUser("小四")
                .set("毛概", 50)
                .set("我的少女时代", 40)
                .set("匆匆那年", 40)
                .set("火星救援", 35)
                .set("许三观卖血记", 45));

        Recommend recommend = new Recommend();
        List<UserCfBook> recommendationBooks = recommend.recommend("小四", users,10 );
        System.out.println("-----------------------");
        System.out.println("推荐结果如下：");
        for (UserCfBook book : recommendationBooks) {
            System.out.println("图书："+book.bookName+" ,评分："+book.score);
        }
    }
}

