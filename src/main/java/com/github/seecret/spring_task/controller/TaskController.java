package com.github.seecret.spring_task.controller;

import com.github.seecret.spring_task.service.TaskService;
import com.github.seecret.spring_task.task.Task;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final Logger LOG = LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> findAllTask() {
        LOG.info("find all task");
        return ResponseEntity.ok(taskService.findAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findTaskById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(taskService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Task> createTask(
            @RequestBody Task task
    ) {
        return ResponseEntity.ok(taskService.create(task));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long id,
            @RequestBody Task task
    ) {
        return ResponseEntity.ok(taskService.update(id, task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(
            Long id
    ) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
