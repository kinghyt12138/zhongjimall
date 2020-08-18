package com.example.zhongjimall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ZhongjimallApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhongjimallApplication.class, args);
    }

}
