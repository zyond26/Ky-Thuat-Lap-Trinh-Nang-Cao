package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentJdbcRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Student> findAll() {
        return jdbcTemplate.query("SELECT id, name, age FROM Student",
                (rs, rowNum) -> new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("age")));
    }

    public void save(Student student) {
        try {
            jdbcTemplate.update("INSERT INTO Student (name, age) VALUES (?, ?)",
                    student.getName(), student.getAge());
        } catch (Exception e) {
            throw new RuntimeException("Failed to insert student: " + e.getMessage(), e);
        }
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Student WHERE id = ?", id);
    }
}