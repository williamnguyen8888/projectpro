package com.william.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "roles", schema = "william_news", catalog = "")
public class RolesEntity {
    private int id;
    private String name;
    private Collection<UsersRolesEntity> usersRolesById;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolesEntity that = (RolesEntity) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "rolesByRoleId")
    public Collection<UsersRolesEntity> getUsersRolesById() {
        return usersRolesById;
    }

    public void setUsersRolesById(Collection<UsersRolesEntity> usersRolesById) {
        this.usersRolesById = usersRolesById;
    }
}
