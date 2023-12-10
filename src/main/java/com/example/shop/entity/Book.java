package com.example.shop.entity;
import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Book {
    private int id;
    private String bookNo;
    @NotBlank(message = "图书名称不能为空")
    private String bookName;
    @NotBlank(message = "图书分类不能为空")
    private String classification;
    @NotNull(message = "价格不能为空")
    @Min(value = 0, message = "价格必须大于等于0")
    private BigDecimal price;
    private int quantity;
    private String description;
    private String pictures;
    private int status;
    private float avgScore;
}
