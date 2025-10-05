package com.github.seecret.spring_task.repository;

import com.github.seecret.spring_task.entity.TaskEntity;
import com.github.seecret.spring_task.dto.task.TaskPriority;
import com.github.seecret.spring_task.dto.task.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    @Query(value = "SELECT t FROM TaskEntity t " +
            "WHERE (:creatorId < t.creatorId " +
            "AND (:createdDate < t.deadlineDate) " +
            "AND (t.createdDate < :deadlineDate))")
    List<TaskEntity> searchTaskByDate(
            @Param("creatorId") Long creatorId,
            @Param("createdDate") LocalDate createdDate,
            @Param("deadlineDate") LocalDate deadlineDate
    );

    @Query(value = "SELECT t FROM TaskEntity t " +
            "WHERE ((:creatorId IS NULL OR t.creatorId = :creatorId) " +
            "AND (:status IS NULL OR t.status = :status) " +
            "AND (:priority IS NULL OR t.priority = :priority))")
    List<TaskEntity> searchTaskByFilter(
            @Param("creatorId") Long creatorId,
            @Param("status") TaskStatus status,
            @Param("priority") TaskPriority priority,
            Pageable pageable
            );
}
