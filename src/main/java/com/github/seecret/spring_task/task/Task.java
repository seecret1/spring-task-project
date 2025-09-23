package com.github.seecret.spring_task.task;

import com.github.seecret.spring_task.task_enum.TaskPriority;
import com.github.seecret.spring_task.task_enum.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Task {

    private Long id;

    private Long creatorId;

    private Long assignedUserId;

    private TaskStatus status;

    private LocalDateTime createdDataTime;

    private LocalDateTime deadlineDate;

    private TaskPriority priority;
}
