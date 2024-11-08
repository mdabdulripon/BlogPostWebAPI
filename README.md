# [BlogPostWebAPI](http://localhost:8088/swagger-ui/index.html#/)

This project empowers merchants to create and manage their own blog posts, enabling them to consistently maintain and update their content

### Must Have TODO (MVP)

- [x] Add Type of the blogs
- [x] Add content blocks, this can replace the body
- [x] Add unit test

  ```
  	@OneToMany(mappedBy = "blogPost", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  	private Set<ContentBlock> contentBlocks;
  ```

### Advance TODO

- [x] Add Automation Test
- [x] Add Comments Entity
  ```
  	@OneToMany(mappedBy = "blogPost", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  	private Set<Comment> comments;
  ```
- [x] Add Like Capability
  ```
  	@OneToMany(mappedBy = "blogPost", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  	private Set<Like> likes;
  ```
- [x] Add Tag Capability
  ```
  	@ManyToMany
  	@JoinTable(
  		name = "PostTag",
  		joinColumns = @JoinColumn(name = "post_id"),
  		inverseJoinColumns = @JoinColumn(name = "tag_id")
  	)
  	private Set<Tag> tags;
  ```
