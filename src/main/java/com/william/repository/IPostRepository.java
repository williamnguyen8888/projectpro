package com.william.repository;

import com.william.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IPostRepository extends PagingAndSortingRepository<PostEntity,Integer> {
    PostEntity findPostEntityByTitle(String title);
    Page<PostEntity> findPostEntitiesByOrderByIdDesc(Pageable pageable);
    Page<PostEntity> findPostEntitiesByCategoryIdOrderByIdDesc(int categoryId, Pageable pageable);
}
