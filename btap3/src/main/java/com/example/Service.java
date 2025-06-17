package main.java.com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Service {
    @Autowired
    private HelloWorld helloWorld;

    public void greet() {
        System.out.println("Greeting from GreetingService...");
        helloWorld.sayHello();
    }
}