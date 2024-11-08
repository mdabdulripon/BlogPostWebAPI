package com.alligator.blog.post.Services;

import com.alligator.blog.post.Shared.Dtos.BlogPostDto;
import com.alligator.blog.post.Shared.Enums.BlogType;

import java.time.OffsetDateTime;
import java.util.List;

public interface BlogPostService {
    BlogPostDto create(BlogPostDto blogPostDto);

    BlogPostDto update(BlogPostDto blogPostDto);

    List<BlogPostDto> findBlogs(int pageNumber, int pageSize, String merchantName, String keyword, BlogType type,
                                OffsetDateTime startDate, OffsetDateTime endDate, String sortBy, String sortDirection);

    BlogPostDto findBlogByPostId(Long postId);

    void delete(Long postId);
}
