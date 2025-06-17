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
        return jdbcTemplate.query("SELECT * FROM Student",
                (rs, rowNum) -> new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("age")));
    }

    public void save(Student student) {
        jdbcTemplate.update("INSERT INTO Student (id, name, age) VALUES (?, ?, ?)",
                student.getId(), student.getName(), student.getAge());
    }

    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM Student WHERE id = ?", id);
    }
}