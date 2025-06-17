package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class btap1 {
    public static void main(String[] args) {
        // Khởi tạo ApplicationContext
        ApplicationContext context = SpringApplication.run(btap1.class, args);

        // Lấy bean HelloWorld từ context
        HelloWorld helloWorld = context.getBean(HelloWorld.class);

        // Gọi phương thức sayHello
        helloWorld.sayHello();
    }
}