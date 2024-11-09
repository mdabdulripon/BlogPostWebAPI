package com.alligator.blog.post.Services;

import com.alligator.blog.post.Entities.BlogPostEntity;
import com.alligator.blog.post.Entities.ContentBlockEntity;
import com.alligator.blog.post.Repositories.BlogPostRepository;
import com.alligator.blog.post.Repositories.ContentBlockRepository;
import com.alligator.blog.post.Shared.BlogPostSpecifications;
import com.alligator.blog.post.Shared.Dtos.BlogPostDto;
import com.alligator.blog.post.Shared.Dtos.ContentBlockDto;
import com.alligator.blog.post.Shared.Enums.BlogType;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    private final BlogPostRepository _blogPostRepository;
    private final ContentBlockRepository _contentBlockRepository;


    BlogPostServiceImpl(BlogPostRepository blogPostRepository, ContentBlockRepository contentBlockRepository) {
        _blogPostRepository = blogPostRepository;
        _contentBlockRepository = contentBlockRepository;
    }

    @Override
    public BlogPostDto create(BlogPostDto blogPostDto) {
        // Set the ID to null to ensure a new record is created
        ModelMapper modelMapper = new ModelMapper();
        BlogPostEntity blogPostEntity = modelMapper.map(blogPostDto, BlogPostEntity.class);
        // Set the ID to null to ensure a new record is created
        blogPostEntity.setPostId(null);

        if (blogPostEntity.getContentBlocks() != null) {
            for (ContentBlockEntity contentBlock : blogPostEntity.getContentBlocks()) {
                contentBlock.setBlogPost(blogPostEntity);
            }
        }
        BlogPostEntity savePost = _blogPostRepository.save(blogPostEntity);
        return modelMapper.map(savePost, BlogPostDto.class);
    }

    @Override
    public BlogPostDto update(BlogPostDto blogPostDto) {
        BlogPostEntity blogPostEntity = _blogPostRepository.findBlogPostByPostId(blogPostDto.getPostId());
        if (blogPostEntity == null) {
            throw new IllegalArgumentException("BlogPost with id " + blogPostDto.getPostId() + " not found");
        }

        ModelMapper modelMapper = new ModelMapper();

        // Update basic fields of the blog post
        blogPostEntity.setTitle(blogPostDto.getTitle());
        blogPostEntity.setSubTitle(blogPostDto.getSubTitle());
        blogPostEntity.setUpdatedAt(blogPostDto.getUpdatedAt());
        blogPostEntity.setStatus(blogPostDto.getStatus());
        blogPostEntity.setMainImageUrl(blogPostDto.getMainImageUrl());
        blogPostEntity.setType(blogPostDto.getType());
        blogPostEntity.setUpdatedAt(OffsetDateTime.now());

        // Update content blocks
        Set<ContentBlockEntity> updatedContentBlocks = new HashSet<>();
        if (blogPostDto.getContentBlocks() != null) {
            for (ContentBlockDto contentBlockDto : blogPostDto.getContentBlocks()) {
                ContentBlockEntity contentBlockEntity = modelMapper.map(contentBlockDto, ContentBlockEntity.class);
                contentBlockEntity.setBlogPost(blogPostEntity);
                updatedContentBlocks.add(contentBlockEntity);
            }
        }

        // Clear existing content blocks and set new ones
        blogPostEntity.getContentBlocks().clear();
        blogPostEntity.getContentBlocks().addAll(updatedContentBlocks);

        BlogPostEntity updatePost = _blogPostRepository.save(blogPostEntity);
        return modelMapper.map(updatePost, BlogPostDto.class);
    }

    @Override
    public List<BlogPostDto> findBlogs(int pageNumber, int pageSize, String merchantName, String keyword, BlogType type,
                                       OffsetDateTime startDate, OffsetDateTime endDate, String sortBy, String sortDirection) {

        if (merchantName == null) {
            throw new IllegalArgumentException("merchantName cannot be null");
        }

        // Determine the sort direction based on the provided parameter
        Sort.Direction direction = sortDirection.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(direction, sortBy));

        Specification<BlogPostEntity> specification = Specification.where(BlogPostSpecifications.hasMerchantName(merchantName))
                .and(BlogPostSpecifications.searchOnTitleOrSubtitle(keyword))  // Search in both title or subTitle
                .and(BlogPostSpecifications.hasType(type))
                .and(BlogPostSpecifications.publishedWithinRange(startDate, endDate));

        Page<BlogPostEntity> blogPostPage = _blogPostRepository.findAll(specification, pageable);

        return blogPostPage.stream().map(blogPost -> new ModelMapper().map(blogPost, BlogPostDto.class)).collect(Collectors.toList());
    }

    @Override
    public BlogPostDto findBlogByPostId(Long postId) {
        BlogPostEntity blogPostEntity = _blogPostRepository.findBlogPostByPostId(postId);
        if (blogPostEntity == null) {
            throw new IllegalArgumentException("BlogPost with id " + postId + " not found");
        }

        return new ModelMapper().map(blogPostEntity, BlogPostDto.class);
    }

    @Override
    public void delete(Long postId) {
        BlogPostEntity blogPostEntity = _blogPostRepository.findBlogPostByPostId(postId);
        if (blogPostEntity == null) {
            throw new IllegalArgumentException("BlogPost with id " + postId + " does not exist");
        }

        _blogPostRepository.delete(blogPostEntity);
    }
}
