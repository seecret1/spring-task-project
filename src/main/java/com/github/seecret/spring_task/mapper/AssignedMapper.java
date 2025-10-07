package com.github.seecret.spring_task.mapper;

import com.github.seecret.spring_task.dto.assigned.Assigned;
import com.github.seecret.spring_task.entity.AssignedEntity;
import org.springframework.stereotype.Component;

@Component
public class AssignedMapper {

    public Assigned toAssigned(AssignedEntity assignedEntity) {
        return new Assigned(
                assignedEntity.getId(),
                assignedEntity.getTaskId(),
                assignedEntity.getFirstNameAndLastName(),
                assignedEntity.getEmail(),
                assignedEntity.getPhone(),
                assignedEntity.getPosition()
        );
    }

    public AssignedEntity toEntity(Assigned assigned) {
        return new AssignedEntity(
                assigned.getId(),
                assigned.getTaskId(),
                assigned.getFirstNameAndLastName(),
                assigned.getEmail(),
                assigned.getPhone(),
                assigned.getPosition()
        );
    }

}
