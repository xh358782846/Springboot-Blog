package com.xhan.blog.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TComment {
    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;

    private boolean adminComment;  //是否为管理员评论
    private Date createTime;
    private Long blogId;
    private TBlog blog;


    private Long parentCommentId;
    private String parentNickname;

    //回复评论
    private List<TComment> replyComments = new ArrayList<>();

    private  TComment parentComment;

    public TComment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public TBlog getBlog() {
        return blog;
    }

    public void setBlog(TBlog blog) {
        this.blog = blog;
    }

    public List<TComment> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<TComment> replyComments) {
        this.replyComments = replyComments;
    }

    public TComment getParentComment() {
        return parentComment;
    }

    public void setParentComment(TComment parentComment) {
        this.parentComment = parentComment;
    }

    public Long getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Long parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getParentNickname() {
        return parentNickname;
    }

    public void setParentNickname(String parentNickname) {
        this.parentNickname = parentNickname;
    }

    public boolean isAdminComment() {
        return adminComment;
    }

    public void setAdminComment(boolean adminComment) {
        this.adminComment = adminComment;
    }

    @Override
    public String toString() {
        return "TComment{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", adminComment=" + adminComment +
                ", createTime=" + createTime +
                ", blogId=" + blogId +
                ", blog=" + blog +
                ", parentCommentId=" + parentCommentId +
                ", parentNickname='" + parentNickname + '\'' +
                ", replyComments=" + replyComments +
                ", parentComment=" + parentComment +
                '}';
    }
}