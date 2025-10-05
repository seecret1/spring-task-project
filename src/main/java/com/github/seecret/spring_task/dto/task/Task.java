package com.github.seecret.spring_task.dto.task;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Task {

    private Long id;

    private Long creatorId;

    private Long assignedUserId;

    private TaskStatus status;

    private LocalDate createdDate;

    private LocalDate deadlineDate;

    private TaskPriority priority;
}
