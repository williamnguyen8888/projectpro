package com.william.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "category_child", schema = "william_news", catalog = "")
public class CategoryChildEntity {
    private int id;
    private int categoryIdParent;
    private String name;
    private Integer isActive;
    private Timestamp createTime;
    private CategoryEntity categoryByCategoryIdParent;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "categoryId_Parent")
    public int getCategoryIdParent() {
        return categoryIdParent;
    }

    public void setCategoryIdParent(int categoryIdParent) {
        this.categoryIdParent = categoryIdParent;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "isActive")
    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryChildEntity that = (CategoryChildEntity) o;
        return id == that.id && categoryIdParent == that.categoryIdParent && Objects.equals(name, that.name) && Objects.equals(isActive, that.isActive) && Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryIdParent, name, isActive, createTime);
    }

    @ManyToOne
    @JoinColumn(name = "categoryId_Parent", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
    @JsonBackReference("category-categoryChild")
    public CategoryEntity getCategoryByCategoryIdParent() {
        return categoryByCategoryIdParent;
    }

    public void setCategoryByCategoryIdParent(CategoryEntity categoryByCategoryIdParent) {
        this.categoryByCategoryIdParent = categoryByCategoryIdParent;
    }
}
