package com.alligator.blog.Controllers;

import com.alligator.blog.Models.Requests.BlogPostCreateRequestModel;
import com.alligator.blog.Models.Requests.BlogPostUpdateRequestModel;
import com.alligator.blog.Models.Requests.QueryParamsRequestModel;
import com.alligator.blog.Models.Response.BlogPostResponseModel;
import com.alligator.blog.Models.Response.OperationStatusResponseModel;
import com.alligator.blog.Services.BlogPostService;
import com.alligator.blog.Services.BlogPostServiceImpl;
import com.alligator.blog.Shared.Dtos.BlogPostDto;
import com.alligator.blog.Shared.Enums.RequestOperationName;
import com.alligator.blog.Shared.Enums.RequestOperationStatus;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {
    private final BlogPostService _blogPostService;

    public BlogController(BlogPostServiceImpl blogPostService) {
        _blogPostService = blogPostService;
    }

    @GetMapping(path = "/{id}")
    public BlogPostResponseModel getBlogPost(@PathVariable Long id) {
        ModelMapper modelMapper = new ModelMapper();
        BlogPostResponseModel blogPostResponse = new BlogPostResponseModel();

        BlogPostDto blogPost = _blogPostService.findBlogById(id);
        return modelMapper.map(blogPost, BlogPostResponseModel.class);
    }

    @PostMapping
    public ResponseEntity<BlogPostResponseModel> CreateBlog(@RequestBody BlogPostCreateRequestModel blogPostCreateRequestModel) {
        ModelMapper modelMapper = new ModelMapper();
        BlogPostDto blogPostDto = modelMapper.map(blogPostCreateRequestModel, BlogPostDto.class);

        BlogPostDto createBlog = _blogPostService.create(blogPostDto);
        BlogPostResponseModel returnValue = modelMapper.map(createBlog, BlogPostResponseModel.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

    @PutMapping
    public BlogPostResponseModel UpdateBlog(@RequestBody BlogPostUpdateRequestModel blogPostUpdateRequestModel) {
        ModelMapper modelMapper = new ModelMapper();
        BlogPostDto blogPostDto = modelMapper.map(blogPostUpdateRequestModel, BlogPostDto.class);

        BlogPostDto updateBlogPostDto = _blogPostService.update(blogPostDto);
        return modelMapper.map(updateBlogPostDto, BlogPostResponseModel.class);
    }


    @DeleteMapping(path = "/{id}")
    public OperationStatusResponseModel deleteBlog(@PathVariable Long id) {
        OperationStatusResponseModel returnValue = new OperationStatusResponseModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());
        _blogPostService.delete(id);
        returnValue.setOperationStatus(RequestOperationStatus.SUCCESS.name());
        return returnValue;
    }


    @GetMapping()
    public List<BlogPostResponseModel> getBlogs(QueryParamsRequestModel params) {
        List<BlogPostResponseModel> returnValue = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        // Call the findBlogs method with OffsetDateTime filters
        List<BlogPostDto> blogPosts = _blogPostService.findBlogs(
                params.getPageNumber(),
                params.getPageSize(),
                params.getMerchantName(),
                params.getKeyword(),
                params.getStartDate(),
                params.getEndDate(),
                params.getSortBy(),
                params.getSortDirection()
        );

        for (BlogPostDto blogPostDto : blogPosts) {
            BlogPostResponseModel blogPostResponseModel = modelMapper.map(blogPostDto, BlogPostResponseModel.class);
            returnValue.add(blogPostResponseModel);
        }

        return returnValue;
    }
}
