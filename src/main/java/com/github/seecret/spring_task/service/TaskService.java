package com.github.seecret.spring_task.service;

import com.github.seecret.spring_task.mapper.TaskMapper;
import com.github.seecret.spring_task.repository.TaskRepository;
import com.github.seecret.spring_task.dto.Task;
import com.github.seecret.spring_task.entity.TaskEntity;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private static final Logger log = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository repository;

    private final TaskMapper mapper;

    public TaskService(TaskRepository repository, TaskMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Task> findAllTasks() {
        log.info("[Service] Find all tasks");

        List<TaskEntity> taskEntities = repository.findAll();

        return taskEntities.stream()
                .map(mapper::toTask)
                .toList();
    }

    public Task findById(Long id) {
        log.info("[Service] Find task by id = {}", id);

        TaskEntity taskEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("[Service] Task not found with id = " + id));

        return mapper.toTask(taskEntity);
    }

    public Task create(Task taskToCreate) {
        log.info("[Service] Create task: {}", taskToCreate);

        var taskToSave = mapper.toEntity(taskToCreate);
        repository.save(taskToSave);

        return mapper.toTask(taskToSave);
    }

    public Task update(Long id, Task taskToUpdate) {
        log.info("[Service] Update task by id = {}", id);

        var taskToSave = mapper.toEntity(taskToUpdate);
        taskToSave.setId(id);
        repository.save(taskToSave);

        return mapper.toTask(taskToSave);
    }

    public void delete(Long id) {
        log.info("[Service] Delete task bt id = {}", id);
        repository.deleteById(id);
    }
}
