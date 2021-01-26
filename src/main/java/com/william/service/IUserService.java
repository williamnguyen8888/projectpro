package com.william.service;

import com.william.entity.UsersEntity;

import java.util.Optional;

public interface IUserService {
    Iterable<UsersEntity> findAll();
    Optional<UsersEntity> findById(int id);
    void save(UsersEntity usersEntity);
    void deleteById(int id);
    boolean existsUsersEntitiesByUsernameAndPassword(String username, String password);
    UsersEntity findUsersEntityByUsername(String username);
}
