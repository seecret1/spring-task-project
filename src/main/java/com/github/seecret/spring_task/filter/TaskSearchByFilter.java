package com.github.seecret.spring_task.filter;

import com.github.seecret.spring_task.task_enum.TaskPriority;
import com.github.seecret.spring_task.task_enum.TaskStatus;

import java.time.LocalDate;

public record TaskSearchByFilter(

        Long creatorId,

        TaskStatus status,

        TaskPriority priority,

        Integer pageSize,

        Integer pageNumber
) {
}
