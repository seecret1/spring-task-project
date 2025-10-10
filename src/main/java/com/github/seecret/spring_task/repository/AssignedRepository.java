package com.github.seecret.spring_task.repository;

import com.github.seecret.spring_task.dto.assigned.AssignedPosition;
import com.github.seecret.spring_task.entity.AssignedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface AssignedRepository extends JpaRepository<AssignedEntity, Long> {

    @Query(value = "SELECT a FROM AssignedEntity a " +
            "WHERE ((:taskId = a.taskId) " +
            "AND (:firstNameAndLastName = a.firstNameAndLastName) " +
            "AND (:email = a.email) " +
            "AND (:phone = a.phone) " +
            "AND (:position = a.position))")
    List<AssignedEntity> searchAssignedByFilter(
            @Param("taskId") Long taskId,
            @Param("firstNameAndLastName") String firstNameAndLastName,
            @Param("email") String email,
            @Param("phone") String phone,
            @Param("position") AssignedPosition position,
            Pageable pageable
    );
}
