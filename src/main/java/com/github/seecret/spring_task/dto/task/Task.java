package com.github.seecret.spring_task.dto.task;

import com.github.seecret.spring_task.dto.assigned.Assigned;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    private Long id;

    private Long userId;

    private TaskStatus status;

    private LocalDate createdDate;

    private LocalDate deadlineDate;

    private TaskPriority priority;

    private List<Assigned> assigneds;
}
