package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String listStudents(Model model) {
        System.out.println("listStudents called");
        model.addAttribute("students", studentService.getAllStudents());
        return "studentList";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "addStudent";
    }

    @PostMapping("/add")
    public String addStudent(@ModelAttribute("student") Student student, Model model) {
        try {
            System.out.println("Adding student: Name=" + student.getName() + ", Age=" + student.getAge());
            studentService.addStudent(student);
            return "redirect:/students";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to add student: " + e.getMessage());
            return "addStudent";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}