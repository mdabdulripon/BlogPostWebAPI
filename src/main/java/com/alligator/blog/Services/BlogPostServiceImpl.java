package com.alligator.blog.Services;

import com.alligator.blog.Entities.BlogPostEntity;
import com.alligator.blog.Repositories.BlogPostRepository;
import com.alligator.blog.Shared.BlogPostSpecifications;
import com.alligator.blog.Shared.Dtos.BlogPostDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    private final BlogPostRepository _blogPostRepository;

    BlogPostServiceImpl(BlogPostRepository blogPostRepository) {
        _blogPostRepository = blogPostRepository;
    }

    @Override
    public BlogPostDto create(BlogPostDto blogPostDto) {
        // Set the ID to null to ensure a new record is created
        ModelMapper modelMapper = new ModelMapper();
        BlogPostEntity blogPostEntity = modelMapper.map(blogPostDto, BlogPostEntity.class);
        // Set the ID to null to ensure a new record is created
        blogPostEntity.setId(null);
        BlogPostEntity savePost = _blogPostRepository.save(blogPostEntity);

        return modelMapper.map(savePost, BlogPostDto.class);
    }

    @Override
    public BlogPostDto update(BlogPostDto blogPostDto) {
        BlogPostEntity blogPostEntity = _blogPostRepository.findBlogPostById(blogPostDto.getId());
        if (blogPostEntity == null) {
            throw new IllegalArgumentException("BlogPost with id " + blogPostDto.getId() + " not found");
        }

        blogPostEntity.setTitle(blogPostDto.getTitle());
        blogPostEntity.setBody(blogPostDto.getBody());
        blogPostEntity.setUpdatedAt(blogPostDto.getUpdatedAt());
        blogPostEntity.setStatus(blogPostDto.getStatus());
        blogPostEntity.setMainImageUrl(blogPostDto.getMainImageUrl());
        blogPostEntity.setType(blogPostDto.getType());

        BlogPostEntity updatePost = _blogPostRepository.save(blogPostEntity);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(updatePost, BlogPostDto.class);
    }

    @Override
    public List<BlogPostDto> findBlogs(int pageNumber, int pageSize, String merchantName, String keyword,
                                       OffsetDateTime startDate, OffsetDateTime endDate, String sortBy, String sortDirection) {

        if (merchantName == null) {
            throw new IllegalArgumentException("merchantName cannot be null");
        }

        // Determine the sort direction based on the provided parameter
        Sort.Direction direction = sortDirection.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(direction, sortBy));

        Specification<BlogPostEntity> specification = Specification.where(BlogPostSpecifications.hasMerchantName(merchantName)).and(BlogPostSpecifications.hasTitleOrBody(keyword))  // Search in both title and body
                .and(BlogPostSpecifications.publishedWithinRange(startDate, endDate));

        Page<BlogPostEntity> blogPostPage = _blogPostRepository.findAll(specification, pageable);

        return blogPostPage.stream().map(blogPost -> new ModelMapper().map(blogPost, BlogPostDto.class)).collect(Collectors.toList());
    }

    @Override
    public BlogPostDto findBlogById(Long id) {
        BlogPostEntity blogPostEntity = _blogPostRepository.findBlogPostById(id);
        if (blogPostEntity == null) {
            throw new IllegalArgumentException("BlogPost with id " + id + " not found");
        }

        return new ModelMapper().map(blogPostEntity, BlogPostDto.class);
    }

    @Override
    public void delete(Long id) {
        BlogPostEntity blogPostEntity = _blogPostRepository.findBlogPostById(id);
        if (blogPostEntity == null) {
            throw new IllegalArgumentException("BlogPost with id " + id + " does not exist");
        }

        _blogPostRepository.delete(blogPostEntity);
    }
}
