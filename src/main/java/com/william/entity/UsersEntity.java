package com.william.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "william_news", catalog = "")
public class UsersEntity {
    private int id;
    private String username;
    private String password;
    private Integer phoneNumber;
    private String address;
    private String avataImg;
    private Integer isactive;
    private Integer role;
    private Timestamp createtime;
    private Collection<CommentEntity> commentsById;
    private Collection<PostEntity> postsById;
    private ActivestatusEntity activestatusByIsactive;
    private RolesEntity rolesByRole;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "phone_number")
    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "avata_img")
    public String getAvataImg() {
        return avataImg;
    }

    public void setAvataImg(String avataImg) {
        this.avataImg = avataImg;
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
    @Column(name = "role")
    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
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
        UsersEntity that = (UsersEntity) o;
        return id == that.id && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(address, that.address) && Objects.equals(avataImg, that.avataImg) && Objects.equals(isactive, that.isactive) && Objects.equals(role, that.role) && Objects.equals(createtime, that.createtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, phoneNumber, address, avataImg, isactive, role, createtime);
    }

    @OneToMany(mappedBy = "usersByUserId")
    @JsonManagedReference
    public Collection<CommentEntity> getCommentsById() {
        return commentsById;
    }

    public void setCommentsById(Collection<CommentEntity> commentsById) {
        this.commentsById = commentsById;
    }

    @OneToMany(mappedBy = "usersByUserId")
    @JsonManagedReference
    public Collection<PostEntity> getPostsById() {
        return postsById;
    }

    public void setPostsById(Collection<PostEntity> postsById) {
        this.postsById = postsById;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "isactive", referencedColumnName = "id", insertable=false, updatable=false)
    @JsonBackReference
    public ActivestatusEntity getActivestatusByIsactive() {
        return activestatusByIsactive;
    }

    public void setActivestatusByIsactive(ActivestatusEntity activestatusByIsactive) {
        this.activestatusByIsactive = activestatusByIsactive;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role", referencedColumnName = "id", insertable=false, updatable=false)
    @JsonBackReference
    public RolesEntity getRolesByRole() {
        return rolesByRole;
    }

    public void setRolesByRole(RolesEntity rolesByRole) {
        this.rolesByRole = rolesByRole;
    }
}
