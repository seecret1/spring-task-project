package com.github.seecret.spring_task.controller;

import com.github.seecret.spring_task.filter.TaskSearchByFilter;
import com.github.seecret.spring_task.service.TaskService;
import com.github.seecret.spring_task.dto.task.Task;
import com.github.seecret.spring_task.dto.task.TaskPriority;
import com.github.seecret.spring_task.dto.task.TaskStatus;
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

    private final Logger log = LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> findAllTask(
            @RequestParam(name = "creatorId", required = false) Long creatorId,
            @RequestParam(name = "status", required = false) TaskStatus status,
            @RequestParam(name = "priority", required = false) TaskPriority priority,
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber
            ) {
        log.info("[Controller] find all tasks using filter");
        var filter = new TaskSearchByFilter(
                creatorId,
                status,
                priority,
                pageSize,
                pageNumber
        );

        return ResponseEntity.ok(taskService.findAllTasks(filter));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findTaskById(
            @PathVariable Long id
    ) {
        log.info("[Controller] find task by id: {}", id);
        return ResponseEntity.ok(taskService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Task> createTask(
            @RequestBody Task task
    ) {
        log.info("[Controller] create task: {}", task);
        return ResponseEntity.ok(taskService.create(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long id,
            @RequestBody Task task
    ) {
        log.info("[Controller] update task by id: {}", id);
        return ResponseEntity.ok(taskService.update(id, task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(
            @PathVariable Long id
    ) {
        log.info("[Controller] delete task by id: {}", id);
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
