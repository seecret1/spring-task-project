package com.github.seecret.spring_task.repository;

import com.github.seecret.spring_task.entity.AssignedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignedRepository extends JpaRepository<AssignedEntity, Long> {
}
