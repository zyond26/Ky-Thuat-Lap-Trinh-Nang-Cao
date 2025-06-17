package main.java.com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class btap3 {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(btap3.class, args);
        GreetingService greetingService = context.getBean(GreetingService.class);
        greetingService.greet();
    }
}