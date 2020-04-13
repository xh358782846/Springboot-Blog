package com.xhan.blog.pojo;

public class TBlogTags {
    private Long id;

    private Long blogsId;

    private Long tagsId;

    public TBlogTags( Long tagsId,Long blogsId) {

        this.tagsId = tagsId;
        this.blogsId = blogsId;
    }

    public Long getBlogsId() {
        return blogsId;
    }

    public void setBlogsId(Long blogsId) {
        this.blogsId = blogsId;
    }

    public Long getTagsId() {
        return tagsId;
    }

    public void setTagsId(Long tagsId) {
        this.tagsId = tagsId;
    }

    @Override
    public String toString() {
        return "TBlogTags{" +
                "id=" + id +
                ", blogsId=" + blogsId +
                ", tagsId=" + tagsId +
                '}';
    }
}