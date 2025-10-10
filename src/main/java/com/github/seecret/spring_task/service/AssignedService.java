package com.github.seecret.spring_task.service;

import com.github.seecret.spring_task.dto.assigned.Assigned;
import com.github.seecret.spring_task.entity.AssignedEntity;
import com.github.seecret.spring_task.filter.AssignedSearchByFilter;
import com.github.seecret.spring_task.mapper.AssignedMapper;
import com.github.seecret.spring_task.repository.AssignedRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AssignedService {

    private final AssignedRepository repository;

    private final AssignedMapper mapper;

    public List<Assigned> findAll(
            AssignedSearchByFilter filter
    ) {
        log.info("[Service] Find all assigneds");

        int pageSize = filter.pageSize() != null
                ? filter.pageSize()
                : 10;
        int pageNum = filter.pageNumber() != null
                ? filter.pageNumber()
                : 0;
        Pageable pageable = PageRequest.of(pageNum, pageSize);

        List<AssignedEntity> assigneds = repository.searchAssignedByFilter(
                filter.taskId(),
                filter.firstNameAndLastName(),
                filter.email(),
                filter.phone(),
                filter.position(),
                pageable
        );
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
