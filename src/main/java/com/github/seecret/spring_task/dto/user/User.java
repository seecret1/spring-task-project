package com.github.seecret.spring_task.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private Long id;

    private String firstNameAndLastName;

    private String email;
}
