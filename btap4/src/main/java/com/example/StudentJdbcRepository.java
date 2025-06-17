package main.java.com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentJdbcRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Thêm sinh viên
    public void addStudent(Student student) {
        String sql = "INSERT INTO Student (name, age) VALUES (?, ?)";
        jdbcTemplate.update(sql, student.getName(), student.getAge());
    }

    // Cập nhật sinh viên
    public void updateStudent(Student student) {
        String sql = "UPDATE Student SET name = ?, age = ? WHERE id = ?";
        jdbcTemplate.update(sql, student.getName(), student.getAge(), student.getId());
    }

    // Xóa sinh viên
    public void deleteStudent(int id) {
        String sql = "DELETE FROM Student WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // Lấy danh sách sinh viên
    public List<Student> getAllStudents() {
        String sql = "SELECT id, name, age FROM Student";
        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("age")));
    }
}