package com.alligator.blog.Shared;

import com.alligator.blog.Entities.BlogPostEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.OffsetDateTime;

public class BlogPostSpecifications {

    public static Specification<BlogPostEntity>  hasMerchantName(String merchantName) {
//        return (root, query, builder) -> builder.equal(root.get("merchantName"), merchantName);
        return (root, query, builder) -> merchantName == null ? builder.conjunction() : builder.equal(root.get("merchantName"), merchantName);
    }

    public static Specification<BlogPostEntity> hasTitle(String title) {
        return (root, query, builder) -> builder.equal(root.get("title"), title);
    }

    public static Specification<BlogPostEntity> publishedAfter(OffsetDateTime startDate) {
        return (root, query, builder) -> startDate == null ? builder.conjunction() : builder.greaterThanOrEqualTo(root.get("createdAt"), startDate);
    }

    public static Specification<BlogPostEntity> publishedBefore(OffsetDateTime endDate) {
        return (root, query, builder) -> endDate == null ? builder.conjunction() : builder.lessThanOrEqualTo(root.get("createdAt"), endDate);
    }
}
