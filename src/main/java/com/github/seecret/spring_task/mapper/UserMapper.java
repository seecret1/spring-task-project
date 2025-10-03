package com.github.seecret.spring_task.mapper;

import com.github.seecret.spring_task.dto.User;
import com.github.seecret.spring_task.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toUser(
            UserEntity userEntity
    ) {
        return new User(
                userEntity.getId(),
                userEntity.getFirstNameAndLastName(),
                userEntity.getEmail()
                );
    }

    public UserEntity toUserEntity(
            User user
    ) {
        return new UserEntity(
                user.getId(),
                user.getFirstNameAndLastName(),
                user.getEmail()
        );
    }
}
