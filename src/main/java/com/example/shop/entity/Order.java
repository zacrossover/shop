package com.example.shop.entity;
import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class Order {
    private int id;
    private String orderNo;
    private int userId;
    private int bookId;
    private String bookName;
    private String classification;
    private BigDecimal price;
    private int quantity;
    private String description;
    private String pictures;
    private int status;
    private int score;
    private int avgScore;
    private Timestamp createTime;
}
