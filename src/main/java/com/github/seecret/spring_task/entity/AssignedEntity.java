package com.github.seecret.spring_task.entity;

import com.github.seecret.spring_task.dto.assigned.AssignedPosition;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "assigned")
@AllArgsConstructor
@NoArgsConstructor
public class AssignedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "first_name_and_last_name")
    private String firstNameAndLastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "position")
    private AssignedPosition position;
}
