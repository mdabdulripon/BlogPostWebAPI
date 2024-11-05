package com.alligator.blog.Services;

import com.alligator.blog.Shared.Dtos.BlogPostDto;
import com.alligator.blog.Shared.Enums.BlogType;

import java.time.OffsetDateTime;
import java.util.List;

public interface BlogPostService {
    BlogPostDto create(BlogPostDto blogPostDto);

    BlogPostDto update(BlogPostDto blogPostDto);

    List<BlogPostDto> findBlogs(int pageNumber, int pageSize, String merchantName, String keyword, BlogType type,
                                OffsetDateTime startDate, OffsetDateTime endDate, String sortBy, String sortDirection);

    BlogPostDto findBlogById(Long id);

    void delete(Long id);
}
