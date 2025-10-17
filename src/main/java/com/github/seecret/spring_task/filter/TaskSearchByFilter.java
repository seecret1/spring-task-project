package com.github.seecret.spring_task.filter;

import com.github.seecret.spring_task.dto.task.TaskPriority;
import com.github.seecret.spring_task.dto.task.TaskStatus;

public record TaskSearchByFilter(

        TaskStatus status,

        TaskPriority priority,

        Integer pageSize,

        Integer pageNumber
) {
}
