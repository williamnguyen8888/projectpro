package com.william.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "comment", schema = "william_news", catalog = "")
public class CommentEntity {
    private int id;
    private int userId;
    private int postId;
    private String content;
    private Integer isactive;
    private Timestamp createtime;
    private UsersEntity usersByUserId;
    private PostEntity postByPostId;
    private ActivestatusEntity activestatusByIsactive;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "postId")
    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        CommentEntity that = (CommentEntity) o;
        return id == that.id && userId == that.userId && postId == that.postId && Objects.equals(content, that.content) && Objects.equals(isactive, that.isactive) && Objects.equals(createtime, that.createtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, postId, content, isactive, createtime);
    }

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false, insertable=false, updatable=false)
    public UsersEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UsersEntity usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "postId", referencedColumnName = "id", nullable = false, insertable=false, updatable=false)
    public PostEntity getPostByPostId() {
        return postByPostId;
    }

    public void setPostByPostId(PostEntity postByPostId) {
        this.postByPostId = postByPostId;
    }

    @ManyToOne
    @JoinColumn(name = "isactive", referencedColumnName = "id", insertable=false, updatable=false)
    public ActivestatusEntity getActivestatusByIsactive() {
        return activestatusByIsactive;
    }

    public void setActivestatusByIsactive(ActivestatusEntity activestatusByIsactive) {
        this.activestatusByIsactive = activestatusByIsactive;
    }
}
