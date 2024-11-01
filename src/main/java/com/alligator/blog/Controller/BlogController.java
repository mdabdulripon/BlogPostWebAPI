package com.alligator.blog.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {
//    private final BlogService blogService;
//
//    public BlogController(BlogService blogService) {
//        this.blogService = blogService;
//    }

    @GetMapping
    public String getBlogs() {
        return "Blogs.....";
    }

//    public BlogDto getBlogs() {
//        return blogService.getBlogs();
//    }

    /*
    @GetMapping({"/{blogId}"})
    public ResponseEntity<BlogDto> getBlogsById(@PathVariable("blogId") UUID blogId) {
        return new ResponseEntity<>(blogService.getBlogsById(blogId), HttpStatus.OK);
    }
    * */

    /*
    @PostMapping
    public ResponseEntity CreateBlog(@RequestBody BlogDto blogDto) {
        BlogDto saveDto = blogService.createBlog(blogDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/blog/" + saveDto.getId().toString());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }
    * */

    /*
    @PutMapping({"/blogId"})
    public ResponseEntity UpdateBlog(@PathVariable("blogId") UUID blogId, @RequestBody BlogDto blogDto) {
        blogService.updateBlog(blogId, blogDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    * */


    /*
    @DeleteMapping({"/blogId"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public  void deleteBlog(@PathVariable("blogId") UUID blogId) {
        blogService.deleteBlog(blogId);
    }
    * */

}