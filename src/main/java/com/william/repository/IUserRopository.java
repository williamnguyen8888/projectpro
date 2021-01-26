package com.william.repository;

import com.william.entity.UsersEntity;
import org.springframework.data.repository.CrudRepository;

public interface IUserRopository extends CrudRepository<UsersEntity, Integer> {
    boolean existsUsersEntitiesByUsernameAndPassword(String username, String password);
    UsersEntity findUsersEntityByUsername(String username);
}
