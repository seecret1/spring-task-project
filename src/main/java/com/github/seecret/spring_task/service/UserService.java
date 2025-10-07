package com.github.seecret.spring_task.service;

import com.github.seecret.spring_task.dto.user.User;
import com.github.seecret.spring_task.entity.UserEntity;
import com.github.seecret.spring_task.mapper.UserMapper;
import com.github.seecret.spring_task.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository repository;

    private final UserMapper mapper;

    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

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

        return mapper.toUser(userEntity);
    }

    public User create(User userToCreate) {
        if (userToCreate.getFirstNameAndLastName().equals("")) {
            throw new IllegalArgumentException("First name and last name cannot be empty");
        }
        if (userToCreate.getFirstNameAndLastName().split(" ").length != 2) {
            throw new IllegalArgumentException("Enter the first name and last name!");
        }

        log.info("[Service] create user {}", userToCreate);

        var userToSave = mapper.toUserEntity(userToCreate);
        repository.save(userToSave);

        return mapper.toUser(userToSave);
    }

    public User update(Long id, User userToUpdate) {
        log.info("[Service] update user by id={}", userToUpdate.getId());

        var userToSave = mapper.toUserEntity(userToUpdate);
        repository.save(userToSave);
        userToSave.setId(id);
        return mapper.toUser(userToSave);
    }

    public void delete(Long id) {
        log.info("[Service] delete user by id={}", id);
        repository.deleteById(id);
    }
}
