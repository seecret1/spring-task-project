package com.github.seecret.spring_task.dto.user;

import com.github.seecret.spring_task.dto.task.Task;
import com.github.seecret.spring_task.entity.TaskEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;

    private String firstNameAndLastName;

    private String email;

    private List<Task> tasks;
}
