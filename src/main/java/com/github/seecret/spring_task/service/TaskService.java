package com.github.seecret.spring_task.service;

import com.github.seecret.spring_task.mapper.TaskMapper;
import com.github.seecret.spring_task.repository.TaskRepository;
import com.github.seecret.spring_task.task.Task;
import com.github.seecret.spring_task.task.TaskEntity;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private static final Logger LOG = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository repository;

    private final TaskMapper mapper;

    public TaskService(TaskRepository repository, TaskMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Task> findAllTasks() {
        LOG.info("Find all tasks");
        return repository.findAll();
    }

    public Task findById(Long id) {
        LOG.info("Find task by id = {}", id);
        return repository.findById(id).get();
    }

    public Task create(Task taskToCreate) {
        LOG.info("Create task: {}", taskToCreate);

        var taskToSave = mapper.toTaskEntity(taskToCreate);
        return repository.save(mapper.toTask(taskToSave));
    }

    public Task update(Long id, Task taskToUpdate) {
        LOG.info("Update task by id = {}", id);

        var taskToSave = mapper.toTaskEntity(taskToUpdate);
        return repository.save(mapper.toTask(taskToSave));
    }

    public void delete(Long id) {
        LOG.info("Delete task bt id = {}", id);
        repository.deleteById(id);
    }
}
