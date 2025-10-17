package com.github.seecret.spring_task.service;

import com.github.seecret.spring_task.dto.user.User;
import com.github.seecret.spring_task.entity.UserEntity;
import com.github.seecret.spring_task.mapper.UserMapper;
import com.github.seecret.spring_task.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final UserMapper mapper;

    public List<User> findAllUsers() {
        log.info("[Service] find all users");
        List<UserEntity> userEntities = repository.findAll();

        return userEntities
                .stream()
                .map(mapper::toUser)
                .toList();
    }

    public User findUserById(Long id) {
        log.info("[Service] find by id={}", id);
        UserEntity userEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("[Service] not found user by id=" + id));

        return mapper.toUserWithoutTasks(userEntity);
    }

    public User create(User userToCreate) {
        if (userToCreate.getFirstNameAndLastName().equals("")) {
            throw new IllegalArgumentException("First name and last name cannot be empty");
        }
        if (userToCreate.getFirstNameAndLastName().split(" ").length != 2) {
            throw new IllegalArgumentException("Enter the first name and last name!");
        }

        log.info("[Service] create user {}", userToCreate);

        var userToSave = mapper.toEntity(userToCreate);
        repository.save(userToSave);

        return mapper.toUser(userToSave);
    }

    public User update(Long id, User userToUpdate) {
        log.info("[Service] update user by id={}", userToUpdate.getId());

        var userToSave = mapper.toEntity(userToUpdate);
        repository.save(userToSave);
        userToSave.setId(id);
        return mapper.toUser(userToSave);
    }

    public void delete(Long id) {
        log.info("[Service] delete user by id={}", id);
        repository.deleteById(id);
    }
}
