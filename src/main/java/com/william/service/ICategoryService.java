package com.william.service;

import com.william.entity.CategoryEntity;

import java.util.Optional;

public interface ICategoryService {
    Iterable<CategoryEntity> findAll();
    Optional<CategoryEntity> findById(int id);
    void save(CategoryEntity categoryEntity);
    void deleteById(int id);
    void delete(CategoryEntity categoryEntity);
    CategoryEntity findCategoryEntityByName(String nameCategory);
}
