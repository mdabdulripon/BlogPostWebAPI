package com.alligator.blog.post.Repositories;

import com.alligator.blog.post.Entities.ContentBlockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentBlockRepository extends JpaRepository<ContentBlockEntity, Long> {
}
