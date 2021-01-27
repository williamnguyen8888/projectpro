package com.william.service;

import com.william.entity.PostEntity;

import java.util.Optional;

public interface IPostService {
    Iterable<PostEntity> findAll();
    Optional<PostEntity> findById(int id);
    void save(PostEntity postEntity);
    void deleteById(int id);
    PostEntity findPostEntityByTitle(String title);
}
