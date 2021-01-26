package com.william.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "activestatus", schema = "william_news", catalog = "")
public class ActivestatusEntity {
    private int id;
    private String status;
    private Collection<CategoryEntity> categoriesById;
    private Collection<CommentEntity> commentsById;
    private Collection<PostEntity> postsById;
    private Collection<PostEntity> postsById_0;
    private Collection<UsersEntity> usersById;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivestatusEntity that = (ActivestatusEntity) o;
        return id == that.id && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status);
    }

    @OneToMany(mappedBy = "activestatusByIsactive")
    @JsonManagedReference
    public Collection<CategoryEntity> getCategoriesById() {
        return categoriesById;
    }

    public void setCategoriesById(Collection<CategoryEntity> categoriesById) {
        this.categoriesById = categoriesById;
    }

    @OneToMany(mappedBy = "activestatusByIsactive")
    @JsonManagedReference
    public Collection<CommentEntity> getCommentsById() {
        return commentsById;
    }

    public void setCommentsById(Collection<CommentEntity> commentsById) {
        this.commentsById = commentsById;
    }

    @OneToMany(mappedBy = "activestatusByCommentStatus")
    @JsonManagedReference
    public Collection<PostEntity> getPostsById() {
        return postsById;
    }

    public void setPostsById(Collection<PostEntity> postsById) {
        this.postsById = postsById;
    }

    @OneToMany(mappedBy = "activestatusByIsactive")
    @JsonManagedReference
    public Collection<PostEntity> getPostsById_0() {
        return postsById_0;
    }

    public void setPostsById_0(Collection<PostEntity> postsById_0) {
        this.postsById_0 = postsById_0;
    }

    @OneToMany(mappedBy = "activestatusByIsactive")
    @JsonManagedReference
    public Collection<UsersEntity> getUsersById() {
        return usersById;
    }

    public void setUsersById(Collection<UsersEntity> usersById) {
        this.usersById = usersById;
    }
}
