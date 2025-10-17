package com.github.seecret.spring_task.dto.assigned;

import com.github.seecret.spring_task.dto.task.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Assigned {

    private Long id;

    private Task task;

    private String firstNameAndLastName;

    private String email;

    private String phone;

    private AssignedPosition position;
}
