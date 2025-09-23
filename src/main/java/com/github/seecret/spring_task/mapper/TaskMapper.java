package com.github.seecret.spring_task.mapper;

import com.github.seecret.spring_task.task.Task;
import com.github.seecret.spring_task.task.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskEntity toTaskEntity(Task task) {
        return new TaskEntity(
                task.getId(),
                task.getCreatorId(),
                task.getAssignedUserId(),
                task.getStatus(),
                task.getCreatedDataTime(),
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
                taskEntity.getCreatedDataTime(),
                taskEntity.getDeadlineDate(),
                taskEntity.getPriority()
        );
    }
}
