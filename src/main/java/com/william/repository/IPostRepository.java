package com.william.repository;

import com.william.entity.PostEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IPostRepository extends PagingAndSortingRepository<PostEntity,Integer> {
    PostEntity findPostEntityByTitle(String title);
}
