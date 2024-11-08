package com.alligator.blog.Repositories;

import com.alligator.blog.Entities.ContentBlockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentBlockRepository extends JpaRepository<ContentBlockEntity, Long> {
}
