package com.alligator.blog.post.Models.Response;

import com.alligator.blog.post.Models.Requests.ContentBlockRequestModel;
import com.alligator.blog.post.Shared.Enums.BlogStatus;
import com.alligator.blog.post.Shared.Enums.BlogType;

import java.time.OffsetDateTime;
import java.util.List;

public class BlogPostResponseModel {
    private Long postId;
    private String userId;
    private String merchantName;
    private String title;
    private String subTitle;
    private String mainImageUrl;
    private BlogType type;
    private BlogStatus status;
    private List<ContentBlockRequestModel> contentBlocks;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;


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

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
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

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
