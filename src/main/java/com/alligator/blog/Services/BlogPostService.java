package com.alligator.blog.Services;

import com.alligator.blog.Shared.Dtos.BlogPostDto;

import java.time.OffsetDateTime;
import java.util.List;

public interface BlogPostService {
    BlogPostDto create(BlogPostDto blogPostDto);
    BlogPostDto update(BlogPostDto blogPostDto);
//    List<BlogPostDto> findBlogs(int pageNumber, int pageSize, String merchantName);
//String merchantName, String title, OffsetDateTime startDate, OffsetDateTime endDate
    List<BlogPostDto> findBlogs(int pageNumber, int pageSize, String merchantName);
    BlogPostDto findBlogById(Long id);
    void delete(Long id);
}
