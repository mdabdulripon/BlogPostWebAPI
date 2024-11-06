package com.alligator.blog.Repositories;

import com.alligator.blog.Entities.BlogPostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPostEntity, Long>, JpaSpecificationExecutor<BlogPostEntity> {
    BlogPostEntity findBlogPostByPostId(Long postId);

    Page<BlogPostEntity> findByMerchantName(String merchantName, Pageable pageable);

    BlogPostEntity findBlogPostsByMerchantName(String merchantName);

    BlogPostEntity findBlogPostsByUserId(String userId);
}
