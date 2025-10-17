package com.github.seecret.spring_task.mapper;

import com.github.seecret.spring_task.dto.task.Task;
import com.github.seecret.spring_task.entity.TaskEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskMapper {

    private final AssignedMapper assignedMapper;

    public TaskEntity toEntity(Task task) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(task.getId());
        taskEntity.setStatus(task.getStatus());
        taskEntity.setCreatedDate(task.getCreatedDate());
        taskEntity.setDeadlineDate(task.getDeadlineDate());
        taskEntity.setPriority(task.getPriority());
        taskEntity.setAssigneds(task.getAssigneds()
                .stream().map(assignedMapper::toEntity).toList());

        return taskEntity;
    }

    public Task toTask(TaskEntity taskEntity) {
        Task task = new Task();
        task.setId(taskEntity.getId());
        task.setStatus(taskEntity.getStatus());
        task.setCreatedDate(taskEntity.getCreatedDate());
        task.setDeadlineDate(taskEntity.getDeadlineDate());
        task.setPriority(taskEntity.getPriority());
        task.setAssigneds(taskEntity.getAssigneds()
                .stream()
                .map(assignedMapper::toAssignedWithoutTask)
                .toList());

        return task;
    }

    public Task toTaskWithoutUser(TaskEntity taskEntity) {
        if (taskEntity == null) {
            return null;
        }

        return new Task(
                taskEntity.getId(),
                null, // User не маппим
                taskEntity.getStatus(),
                taskEntity.getCreatedDate(),
                taskEntity.getDeadlineDate(),
                taskEntity.getPriority(),
                taskEntity.getAssigneds() != null ?
                        taskEntity.getAssigneds()
                                .stream()
                                .map(assignedMapper::toAssignedWithoutTask)
                                .toList() : null
        );
    }
}
