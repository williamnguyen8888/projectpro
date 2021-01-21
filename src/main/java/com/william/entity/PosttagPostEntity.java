package com.william.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "posttag_post", schema = "william_news", catalog = "")
public class PosttagPostEntity {
    private int id;
    private Integer postid;
    private Integer postTagId;
    private PostEntity postByPostid;
    private PosttagEntity posttagByPostTagId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "postid")
    public Integer getPostid() {
        return postid;
    }

    public void setPostid(Integer postid) {
        this.postid = postid;
    }

    @Basic
    @Column(name = "postTagId")
    public Integer getPostTagId() {
        return postTagId;
    }

    public void setPostTagId(Integer postTagId) {
        this.postTagId = postTagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PosttagPostEntity that = (PosttagPostEntity) o;
        return id == that.id && Objects.equals(postid, that.postid) && Objects.equals(postTagId, that.postTagId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, postid, postTagId);
    }

    @ManyToOne
    @JoinColumn(name = "postid", referencedColumnName = "id", insertable=false, updatable=false)
    public PostEntity getPostByPostid() {
        return postByPostid;
    }

    public void setPostByPostid(PostEntity postByPostid) {
        this.postByPostid = postByPostid;
    }

    @ManyToOne
    @JoinColumn(name = "postTagId", referencedColumnName = "id", insertable=false, updatable=false)
    public PosttagEntity getPosttagByPostTagId() {
        return posttagByPostTagId;
    }

    public void setPosttagByPostTagId(PosttagEntity posttagByPostTagId) {
        this.posttagByPostTagId = posttagByPostTagId;
    }
}
