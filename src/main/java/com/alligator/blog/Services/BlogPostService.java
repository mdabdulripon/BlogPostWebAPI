package com.alligator.blog.Services;

import com.alligator.blog.Shared.Dtos.BlogPostDto;

import java.util.List;

public interface BlogPostService {
    BlogPostDto create(BlogPostDto blogPostDto);
    BlogPostDto update(BlogPostDto blogPostDto);
    List<BlogPostDto> findBlogs(int pageNumber, int pageSize, String merchantName);
    BlogPostDto findBlogById(Long id);
    void delete(Long id);
}
