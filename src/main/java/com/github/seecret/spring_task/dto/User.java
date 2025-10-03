package com.github.seecret.spring_task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private Long id;

    private String firstNameAndLastName;

    private String email;
}
