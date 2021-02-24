package com.william.service;

import com.william.entity.PostEntity;
import com.william.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PostService implements IPostService {
    @Autowired
    private IPostRepository postRepository;

    @Override
    public Iterable<PostEntity> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<PostEntity> findById(int id) {
        return postRepository.findById(id);
    }

    @Override
    public void save(PostEntity postEntity) {
        postRepository.save(postEntity);
    }

    @Override
    public void deleteById(int id) {
        postRepository.deleteById(id);
    }

    @Override
    public PostEntity findPostEntityByTitle(String title) {
        return postRepository.findPostEntityByTitle(title);
    }
    @Override
    public Page<PostEntity> findPostEntitiesByOrderByIdDesc(Pageable pageable){return  postRepository.findPostEntitiesByOrderByIdDesc(pageable);}

    @Override
    public Page<PostEntity> findPostEntitiesByCategoryIdOrderByIdDesc(int categoryId, Pageable pageable) {
        return postRepository.findPostEntitiesByCategoryIdOrderByIdDesc(categoryId,pageable);
    }
}

