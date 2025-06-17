package main.java.com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GreetingService {
    @Autowired
    private HelloWorld helloWorld; // Tiêm dependency HelloWorld

    public void greet() {
        System.out.println("Greeting from GreetingService...");
        helloWorld.sayHello(); // Gọi phương thức từ bean HelloWorld
    }
}