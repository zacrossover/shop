package com.example.shop.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import com.example.shop.userCf.BookRatings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.shop.dto.Result;
import com.example.shop.entity.Book;
import com.example.shop.service.RecommandService;
import com.example.shop.util.ImagePathUtil;

import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/recommand")
public class RecommandController {

    final static Logger logger = LoggerFactory.getLogger(RecommandController.class);


    @Autowired
    private RecommandService recommandService;

    /**
     * 查询前nums个推荐图书String myUserId,int nums
     * @return
     */
    @GetMapping("/recommandBooks")
    public Result recommandBooks() {
        //return new Result(1, "查询成功", recommandService.recommandBooks(myUserId, nums));
        /*test
        BookRatings.addRating("2", 20);
        BookRatings.addRating("3", 30);
        BookRatings.addRating("4", 50);
        return new Result(1, "查询成功", recommandService.recommandBooks(1, 10));
        */
        return new Result(1, "查询成功", recommandService.recommandBooks("zhangjing", 10));
    }
}
