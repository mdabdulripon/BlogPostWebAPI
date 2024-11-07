package com.alligator.blog.Entities;

import com.alligator.blog.Shared.Enums.ContentType;
import jakarta.persistence.*;

@Entity(name = "content_blocks")
public class ContentBlockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentId;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private BlogPostEntity blogPost;

    private ContentType contentType;
    private Integer contentOrder;

    @Lob
    private String contentText;  // Store paragraph, image URL, or code snippet

    private String language;  // For code snippets only


    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public BlogPostEntity getBlogPost() {
        return blogPost;
    }

    public void setBlogPost(BlogPostEntity blogPost) {
        this.blogPost = blogPost;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public Integer getContentOrder() {
        return contentOrder;
    }

    public void setContentOrder(Integer contentOrder) {
        this.contentOrder = contentOrder;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
