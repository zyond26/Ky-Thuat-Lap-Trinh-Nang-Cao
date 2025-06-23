package main.java.com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class btap4 {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(btap4.class, args);
        StudentJdbcRepository repository = context.getBean(StudentJdbcRepository.class);

        // Thêm một sinh viên mẫu (tùy chọn)
        repository.addStudent(new Student(0, "hung", 20));
        repository.addStudent(new Student(0, "Dieu", 20));
        repository.addStudent(new Student(0, "Bac", 20));
        repository.addStudent(new Student(0, "Ngoc", 20));
        repository.addStudent(new Student(0, "Nhi ", 20));

        // Hiển thị danh sách sinh viên
        List<Student> students = repository.getAllStudents();
        System.out.println("List of Students:");
        for (Student student : students) {
            System.out.println(student);
        }
    }
}