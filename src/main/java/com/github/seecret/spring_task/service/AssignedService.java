package com.github.seecret.spring_task.service;

import com.github.seecret.spring_task.dto.assigned.Assigned;
import com.github.seecret.spring_task.entity.AssignedEntity;
import com.github.seecret.spring_task.mapper.AssignedMapper;
import com.github.seecret.spring_task.repository.AssignedRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignedService {

    private static final Logger log = LoggerFactory.getLogger(AssignedService.class);

    private final AssignedRepository repository;

    private final AssignedMapper mapper;

    public AssignedService(AssignedRepository repository, AssignedMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Assigned> findAll() {
        log.info("[Service] Find all assigneds");

        List<AssignedEntity> assigneds = repository.findAll();
        return assigneds
                .stream()
                .map(mapper::toAssigned)
                .toList();
    }

    public Assigned findById(Long id) {
        log.info("[Service] Find assigned by id = {}", id);

        var assigned = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("[Service] Assigned not found with id = " + id));
        return mapper.toAssigned(assigned);
    }

    public Assigned create(Assigned assignedToCreate) {
        log.info("[Service] Create assigned: {}", assignedToCreate);

        var assignedToSave = mapper.toEntity(assignedToCreate);
        repository.save(assignedToSave);
        return mapper.toAssigned(assignedToSave);
    }

    public Assigned update(Long id, Assigned assignedToUpdate) {
        log.info("[Service] Update assigned by id = {}", assignedToUpdate.getId());

        var assignedToSave = mapper.toEntity(assignedToUpdate);
        assignedToSave.setId(id);
        repository.save(assignedToSave);
        return mapper.toAssigned(assignedToSave);
    }

    public void delete(Long id) {
        log.info("[Service] Delete assigned by id = {}", id);
        repository.deleteById(id);
    }
}
