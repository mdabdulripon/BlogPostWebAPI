package com.alligator.blog.post.Models.Requests;

import com.alligator.blog.post.Shared.Enums.BlogStatus;
import com.alligator.blog.post.Shared.Enums.BlogType;

import java.util.List;

public class BlogPostUpdateRequestModel {
    private Long postId;
    private String userId;
    private String merchantName;
    private String title;
    private String body;
    private String mainImageUrl;
    private BlogType type;
    private BlogStatus status;
    private List<ContentBlockRequestModel> contentBlocks;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getMainImageUrl() {
        return mainImageUrl;
    }

    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }

    public BlogType getType() {
        return type;
    }

    public void setType(BlogType type) {
        this.type = type;
    }

    public BlogStatus getStatus() {
        return status;
    }

    public void setStatus(BlogStatus status) {
        this.status = status;
    }

    public List<ContentBlockRequestModel> getContentBlocks() {
        return contentBlocks;
    }

    public void setContentBlocks(List<ContentBlockRequestModel> contentBlocks) {
        this.contentBlocks = contentBlocks;
    }
}
