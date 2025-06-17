package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentJdbcRepository repository;

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public void addStudent(Student student) {
        repository.save(student);
    }

    public void deleteStudent(int id) {
        repository.deleteById(id);
    }
}