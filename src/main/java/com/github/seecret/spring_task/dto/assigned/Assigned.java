package com.github.seecret.spring_task.dto.assigned;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Assigned {

    private Long id;

    private Long taskId;

    private String firstNameAndLastName;

    private String email;

    private String phone;

    private AssignedPosition position;

}
