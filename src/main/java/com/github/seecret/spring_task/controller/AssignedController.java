package com.github.seecret.spring_task.controller;

import com.github.seecret.spring_task.dto.assigned.Assigned;
import com.github.seecret.spring_task.service.AssignedService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assigned")
@RequiredArgsConstructor
public class AssignedController {

    private static final Logger log = LoggerFactory.getLogger(AssignedController.class);

    private final AssignedService assignedService;

    @GetMapping
    public ResponseEntity<List<Assigned>> findAllAssigneds() {
        log.info("[Controller] find all assigneds");
        return ResponseEntity.ok(assignedService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assigned> findAssignedById(@PathVariable Long id) {
        log.info("[Controller] find assigned by id = {}", id);
        return ResponseEntity.ok(assignedService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Assigned> createAssigned(@RequestBody Assigned assigned) {
        log.info("[Controller] create assigned: {}", assigned);
        return ResponseEntity.ok(assignedService.create(assigned));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Assigned> updateAssigned(
            @PathVariable Long id, @RequestBody Assigned assigned
    ) {
        log.info("[Controller] update assigned by id = {}", id);
        return ResponseEntity.ok(assignedService.update(id, assigned));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Assigned>  deleteAssigned(@PathVariable Long id) {
        log.info("[Controller] delete assigned by id = {}", id);
        assignedService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
