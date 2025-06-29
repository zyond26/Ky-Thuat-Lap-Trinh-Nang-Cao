package com.example.ManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.ManagementSystem.model.Task;
import com.example.ManagementSystem.model.TaskStatus;
import com.example.ManagementSystem.repository.TaskRepository;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    // Get all tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Get task by ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create new task
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        if (task.getStatus() == null) {
            task.setStatus(TaskStatus.PENDING);
        }
        Task savedTask = taskRepository.save(task);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTask.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedTask);
    }

    // Update task
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(taskDetails.getTitle());
                    task.setDescription(taskDetails.getDescription());
                    task.setStatus(taskDetails.getStatus());
                    task.setDueDate(taskDetails.getDueDate());
                    Task updatedTask = taskRepository.save(task);
                    return ResponseEntity.ok(updatedTask);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete task
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        return taskRepository.findById(id)
                .map(task -> {
                    taskRepository.delete(task);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get tasks by status
    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    // Get overdue tasks
    @GetMapping("/overdue")
    public List<Task> getOverdueTasks() {
        return taskRepository.findByDueDateBeforeAndStatusNot(
                LocalDate.now(), TaskStatus.COMPLETED);
    }
}