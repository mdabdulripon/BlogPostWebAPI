package com.alligator.blog.Models.Requests;

import com.alligator.blog.Shared.Enums.ContentType;

public class ContentBlockRequestModel {
    private Long contentId;
    private ContentType type; // E.g., PARAGRAPH, IMAGE, CODE_SNIPPET
    private Integer contentOrder;
    private String contentText; // For paragraph text, image URL, or code snippet
    private String language;    // Optional, for code snippets only

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public ContentType getType() {
        return type;
    }

    public void setType(ContentType type) {
        this.type = type;
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
