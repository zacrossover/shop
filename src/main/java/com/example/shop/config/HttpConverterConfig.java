package com.example.shop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.shop.util.ImagePathUtil;

@Configuration
public class HttpConverterConfig implements WebMvcConfigurer {
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//将url中image路径下的请求映射到磁盘对应的路径
        registry.addResourceHandler("/image/**").addResourceLocations("file:" + ImagePathUtil.getImagePath());
    }

}
