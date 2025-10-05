package com.github.seecret.spring_task.dto.assigned;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Assigned {

    private Long id;

    private Long taskId;

    private String firstNameAndLastName;

    private String email;

    private String phone;

    private AssignedPosition position;

}
