package com.github.seecret.spring_task.entity;

import com.github.seecret.spring_task.dto.assigned.AssignedPosition;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id")
    private TaskEntity task;

    @Column(name = "first_name_and_last_name")
    private String firstNameAndLastName;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "position")
    private AssignedPosition position;
}
