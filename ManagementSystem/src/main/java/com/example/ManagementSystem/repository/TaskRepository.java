package com.example.ManagementSystem.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// Update the import path to the correct package of Task
import com.example.ManagementSystem.model.Task;
import com.example.ManagementSystem.model.TaskStatus;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAll();

    Optional<Task> findById(Long id);

    Task save(Task task);

    void delete(Task task);

    List<Task> findById(TaskStatus status);

    List<Task> findByDueDateBeforeAndStatusNot(LocalDate now, TaskStatus completed);

    List<Task> findByStatus(TaskStatus status);
}