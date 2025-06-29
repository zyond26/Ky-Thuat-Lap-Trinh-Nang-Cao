package com.example.DemoProject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CalculatorController {

    @GetMapping("/")
    public String showForm() {
        return "index";
    }

    @PostMapping("/solve")
    public String solveEquation(Calculator calc, Model model) {
        String result = calc.solve();
        model.addAttribute("result", result);
        return "index";
    }
}