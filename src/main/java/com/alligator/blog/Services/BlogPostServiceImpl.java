package com.alligator.blog.Services;

import com.alligator.blog.Entities.BlogPostEntity;
import com.alligator.blog.Repositories.BlogPostRepository;
import com.alligator.blog.Shared.Dtos.BlogPostDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    @Autowired
    BlogPostRepository _blogPostRepository;

    @Override
    public BlogPostDto create(BlogPostDto blogPostDto) {
        ModelMapper modelMapper = new ModelMapper();
        BlogPostEntity blogPostEntity = modelMapper.map(blogPostDto, BlogPostEntity.class);
        BlogPostEntity savePost = _blogPostRepository.save(blogPostEntity);

        return modelMapper.map(savePost, BlogPostDto.class);
    }

    @Override
    public BlogPostDto update(BlogPostDto blogPostDto) {
        BlogPostEntity blogPostEntity = _blogPostRepository.findBlogPostById(blogPostDto.getId());
        if (blogPostEntity == null) {
            return null; // TODO: Throw Exception
        }

        blogPostEntity.setTitle(blogPostDto.getTitle());
        blogPostEntity.setBody(blogPostDto.getBody());
        blogPostEntity.setUpdatedAt(blogPostDto.getUpdatedAt());
        blogPostEntity.setStatus(blogPostDto.getStatus());
        blogPostEntity.setMainImageUrl(blogPostDto.getMainImageUrl());

        BlogPostEntity updatePost = _blogPostRepository.save(blogPostEntity);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(updatePost, BlogPostDto.class);
    }

    @Override
    public List<BlogPostDto> findBlogs(int pageNumber, int pageSize, String merchantName) {
        return List.of();
    }

    @Override
    public BlogPostDto findBlogById(Long id) {
        BlogPostEntity blogPostEntity = _blogPostRepository.findBlogPostById(id);
        if (blogPostEntity == null) {
            return null; // TODO: Throw Exception
        }

        return new ModelMapper().map(blogPostEntity, BlogPostDto.class);
    }

    @Override
    public void delete(Long id) {
        BlogPostEntity blogPostEntity = _blogPostRepository.findBlogPostById(id);
        if (blogPostEntity == null) {
            return; // TODO: Throw Exception
        }

        _blogPostRepository.delete(blogPostEntity);
    }
}
