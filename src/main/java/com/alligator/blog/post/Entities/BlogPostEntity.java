package com.alligator.blog.post.Entities;

import com.alligator.blog.post.Shared.Enums.BlogStatus;
import com.alligator.blog.post.Shared.Enums.BlogType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;

import java.time.OffsetDateTime;
import java.util.Set;


@Entity(name = "blog_posts")
public class BlogPostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String userId; // Reference to the User ID in UserWebAPI
    private String merchantName;
    private String title;
    private String subTitle;
    private String mainImageUrl;
    private BlogType type;  // TODO: Rename this to CategoryType
    private BlogStatus status;

    @OneToMany(mappedBy = "blogPost", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ContentBlockEntity> contentBlocks;

    /*
    @ManyToMany
    @JoinTable(
        name = "blog_post_tags",
        joinColumns = @JoinColumn(name = "blog_post_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();
    * */


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

    public Set<ContentBlockEntity> getContentBlocks() {
        return contentBlocks;
    }

    public void setContentBlocks(Set<ContentBlockEntity> contentBlocks) {
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
