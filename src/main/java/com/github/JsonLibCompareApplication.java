package com.github;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by bs.yang on 11/27/2018.
 */

@EnableScheduling
@SpringBootApplication
public class JsonLibCompareApplication {
    public static void main(String[] args) {
        SpringApplication.run(JsonLibCompareApplication.class, args);
    }
}
