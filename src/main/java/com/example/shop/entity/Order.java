package com.example.shop.entity;
import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class Order {
    private int id;
    private String orderNo;
    private String username;
    private int bookId;
    private String bookName;
    private String classification;
    private BigDecimal price;
    private int quantity;
    private String description;
    private String pictures;
    private int status;
    private float score;
    private int avgScore;
    private Timestamp createTime;
    private int score1;
    private int score2;
    private int score3;
    private int score4;
}
