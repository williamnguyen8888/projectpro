package com.william.repository;

import com.william.entity.CategoryEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICategoryRepository extends PagingAndSortingRepository<CategoryEntity, Integer> {
CategoryEntity findCategoryEntityByName(String nameCategory);
}
