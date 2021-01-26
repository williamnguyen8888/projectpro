package com.william.service;

import com.william.entity.UsersEntity;
import com.william.repository.IUserRopository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRopository userRopository;

    @Override
    public Iterable<UsersEntity> findAll() {
        return userRopository.findAll();
    }

    @Override
    public Optional<UsersEntity> findById(int id) {
        return userRopository.findById(id);
    }

    @Override
    public void save(UsersEntity usersEntity) {
        userRopository.save(usersEntity);
    }

    @Override
    public void deleteById(int id) {
userRopository.deleteById(id);
    }

    @Override
    public UsersEntity findUsersEntityByUsername(String username) {
        return userRopository.findUsersEntityByUsername(username);
    }

    @Override
    public boolean existsUsersEntitiesByUsernameAndPassword(String username, String password) {
        return userRopository.existsUsersEntitiesByUsernameAndPassword(username, password);
    }
}
