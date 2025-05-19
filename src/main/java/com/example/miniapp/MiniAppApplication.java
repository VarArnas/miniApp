package com.example.miniapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class MiniAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiniAppApplication.class, args);
    }
}
