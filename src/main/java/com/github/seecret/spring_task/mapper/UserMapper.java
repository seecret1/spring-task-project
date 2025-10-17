package com.github.seecret.spring_task.mapper;

import com.github.seecret.spring_task.dto.user.User;
import com.github.seecret.spring_task.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final TaskMapper taskMapper;

    public UserEntity toEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setFirstNameAndLastName(user.getFirstNameAndLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setTasks(user.getTasks()
                .stream()
                .map(taskMapper::toEntity)
                .toList());

        return userEntity;
    }

    public User toUser(
            UserEntity userEntity
    ) {
        User user = new User();
        user.setId(userEntity.getId());
        user.setFirstNameAndLastName(userEntity.getFirstNameAndLastName());
        user.setEmail(userEntity.getEmail());
        user.setTasks(
                userEntity.getTasks()
                        .stream()
                        .map(taskMapper::toTaskWithoutUser)
                        .toList()
        );

        return user;
    }

    public User toUserWithoutTasks(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        return new User(
                userEntity.getId(),
                userEntity.getFirstNameAndLastName(),
                userEntity.getEmail(),
                null
        );
    }
}
