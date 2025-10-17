package com.github.seecret.spring_task.mapper;

import com.github.seecret.spring_task.dto.assigned.Assigned;
import com.github.seecret.spring_task.entity.AssignedEntity;
import com.github.seecret.spring_task.entity.TaskEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssignedMapper {

    public AssignedEntity toEntity(Assigned assigned) {
        AssignedEntity assignedEntity = new AssignedEntity();
        assignedEntity.setId(assigned.getId());
        assignedEntity.setFirstNameAndLastName(assigned.getFirstNameAndLastName());
        assignedEntity.setEmail(assigned.getEmail());
        assignedEntity.setPhone(assigned.getPhone());
        assignedEntity.setPosition(assigned.getPosition());

        return assignedEntity;
    }


    public Assigned toAssigned(AssignedEntity entity) {
        Assigned assigned = new Assigned();
        assigned.setId(entity.getId());
        assigned.setFirstNameAndLastName(entity.getFirstNameAndLastName());
        assigned.setEmail(entity.getEmail());
        assigned.setPhone(entity.getPhone());
        assigned.setPosition(entity.getPosition());

        return assigned;
    }

    public Assigned toAssignedWithoutTask(AssignedEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Assigned(
                entity.getId(),
                null,
                entity.getFirstNameAndLastName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getPosition()
        );
    }
}
