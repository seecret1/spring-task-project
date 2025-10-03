package com.github.seecret.spring_task.service;

import com.github.seecret.spring_task.filter.TaskSearchByFilter;
import com.github.seecret.spring_task.mapper.TaskMapper;
import com.github.seecret.spring_task.repository.TaskRepository;
import com.github.seecret.spring_task.dto.Task;
import com.github.seecret.spring_task.entity.TaskEntity;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private static final Logger log = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository repository;

    private final TaskMapper mapper;

    @Value("${task.page.size}")
    private int pageableSize;

    @Value("${task.page.number}")
    private int pageableNumber;

    public TaskService(TaskRepository repository, TaskMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Task> findAllTasks(
            TaskSearchByFilter filter
    ) {
        int pageSize = filter.pageSize() != null
                ? filter.pageSize() : pageableSize;
        int pageNum = filter.pageNumber() != null
                ? filter.pageNumber() : pageableNumber;

        var pageable = Pageable.ofSize(pageSize).withPage(pageNum);

        List<TaskEntity> taskEntities = repository.searchTaskByFilter(
                filter.creatorId(),
                filter.status(),
                filter.priority(),
                pageable
        );

        log.info("[Service] Find all tasks");
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
