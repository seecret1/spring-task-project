package com.github.seecret.spring_task.filter;

import com.github.seecret.spring_task.dto.assigned.AssignedPosition;

public record AssignedSearchByFilter(

        String firstNameAndLastName,

        String email,

        String phone,

        AssignedPosition position,

        Integer pageSize,

        Integer pageNumber
) {
}
