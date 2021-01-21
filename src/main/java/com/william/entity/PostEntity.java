package com.william.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "post", schema = "william_news", catalog = "")
public class PostEntity {
    private int id;
    private String title;
    private int userId;
    private int categoryId;
    private Integer layoutId;
    private String photo;
    private String content;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private Integer commentStatus;
    private Integer isactive;
    private Timestamp createtime;
    private Collection<CommentEntity> commentsById;
    private UsersEntity usersByUserId;
    private CategoryEntity categoryByCategoryId;
    private ActivestatusEntity activestatusByCommentStatus;
    private ActivestatusEntity activestatusByIsactive;
    private Collection<PosttagPostEntity> posttagPostsById;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    @Column(name = "categoryId")
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "layoutId")
    public Integer getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(Integer layoutId) {
        this.layoutId = layoutId;
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
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "view_count")
    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    @Basic
    @Column(name = "comment_count")
    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    @Basic
    @Column(name = "like_count")
    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    @Basic
    @Column(name = "comment_status")
    public Integer getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Integer commentStatus) {
        this.commentStatus = commentStatus;
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
        PostEntity that = (PostEntity) o;
        return id == that.id && userId == that.userId && categoryId == that.categoryId && Objects.equals(title, that.title) && Objects.equals(layoutId, that.layoutId) && Objects.equals(photo, that.photo) && Objects.equals(content, that.content) && Objects.equals(viewCount, that.viewCount) && Objects.equals(commentCount, that.commentCount) && Objects.equals(likeCount, that.likeCount) && Objects.equals(commentStatus, that.commentStatus) && Objects.equals(isactive, that.isactive) && Objects.equals(createtime, that.createtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, userId, categoryId, layoutId, photo, content, viewCount, commentCount, likeCount, commentStatus, isactive, createtime);
    }

    @OneToMany(mappedBy = "postByPostId")
    public Collection<CommentEntity> getCommentsById() {
        return commentsById;
    }

    public void setCommentsById(Collection<CommentEntity> commentsById) {
        this.commentsById = commentsById;
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
    @JoinColumn(name = "categoryId", referencedColumnName = "id", nullable = false, insertable=false, updatable=false)
    public CategoryEntity getCategoryByCategoryId() {
        return categoryByCategoryId;
    }

    public void setCategoryByCategoryId(CategoryEntity categoryByCategoryId) {
        this.categoryByCategoryId = categoryByCategoryId;
    }

    @ManyToOne
    @JoinColumn(name = "comment_status", referencedColumnName = "id", insertable=false, updatable=false)
    public ActivestatusEntity getActivestatusByCommentStatus() {
        return activestatusByCommentStatus;
    }

    public void setActivestatusByCommentStatus(ActivestatusEntity activestatusByCommentStatus) {
        this.activestatusByCommentStatus = activestatusByCommentStatus;
    }

    @ManyToOne
    @JoinColumn(name = "isactive", referencedColumnName = "id", insertable=false, updatable=false)
    public ActivestatusEntity getActivestatusByIsactive() {
        return activestatusByIsactive;
    }

    public void setActivestatusByIsactive(ActivestatusEntity activestatusByIsactive) {
        this.activestatusByIsactive = activestatusByIsactive;
    }

    @OneToMany(mappedBy = "postByPostid")
    public Collection<PosttagPostEntity> getPosttagPostsById() {
        return posttagPostsById;
    }

    public void setPosttagPostsById(Collection<PosttagPostEntity> posttagPostsById) {
        this.posttagPostsById = posttagPostsById;
    }
}
