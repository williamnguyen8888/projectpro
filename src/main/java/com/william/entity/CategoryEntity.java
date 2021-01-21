package com.william.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "category", schema = "william_news", catalog = "")
public class CategoryEntity {
    private int id;
    private String name;
    private String photo;
    private String description;
    private Integer isactive;
    private Timestamp createtime;
    private ActivestatusEntity activestatusByIsactive;
    private Collection<PostEntity> postsById;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "photo")
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "isactive")
    public Integer getIsactive() {
        return isactive;
    }

    public void setIsactive(Integer isactive) {
        this.isactive = isactive;
    }

    @Basic
    @Column(name = "createtime")
    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity that = (CategoryEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(photo, that.photo) && Objects.equals(description, that.description) && Objects.equals(isactive, that.isactive) && Objects.equals(createtime, that.createtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, photo, description, isactive, createtime);
    }

    @ManyToOne
    @JoinColumn(name = "isactive", referencedColumnName = "id", insertable=false, updatable=false)
    public ActivestatusEntity getActivestatusByIsactive() {
        return activestatusByIsactive;
    }

    public void setActivestatusByIsactive(ActivestatusEntity activestatusByIsactive) {
        this.activestatusByIsactive = activestatusByIsactive;
    }

    @OneToMany(mappedBy = "categoryByCategoryId")
    public Collection<PostEntity> getPostsById() {
        return postsById;
    }

    public void setPostsById(Collection<PostEntity> postsById) {
        this.postsById = postsById;
    }
}
