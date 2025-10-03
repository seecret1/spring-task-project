package com.github.seecret.spring_task.mapper;

import com.github.seecret.spring_task.dto.Task;
import com.github.seecret.spring_task.entity.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskEntity toEntity(Task task) {
        return new TaskEntity(
                task.getId(),
                task.getCreatorId(),
                task.getAssignedUserId(),
                task.getStatus(),
                task.getCreatedDate(),
                task.getDeadlineDate(),
                task.getPriority()
        );
    }

    public Task toTask(TaskEntity taskEntity) {
        return new Task(
                taskEntity.getId(),
                taskEntity.getCreatorId(),
                taskEntity.getAssignedUserId(),
                taskEntity.getStatus(),
                taskEntity.getCreatedDate(),
                taskEntity.getDeadlineDate(),
                taskEntity.getPriority()
        );
    }
}
