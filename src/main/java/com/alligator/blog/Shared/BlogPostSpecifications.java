package com.alligator.blog.Shared;

import com.alligator.blog.Entities.BlogPostEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.OffsetDateTime;

public class BlogPostSpecifications {

    public static Specification<BlogPostEntity> hasMerchantName(String merchantName) {
        return (root, query, builder) -> builder.equal(root.get("merchantName"), merchantName);
    }

    // perform search on the body & title fields
    public static Specification<BlogPostEntity> hasTitleOrBody(String keyword) {
        return (root, query, builder) -> {
            if (keyword == null || keyword.isEmpty()) {
                return builder.conjunction();
            }
            // `OR` condition to match keyword in either title or body
            return builder.or(
                    builder.like(root.get("title"), "%" + keyword + "%"),
                    builder.like(root.get("body"), "%" + keyword + "%")
            );
        };
    }

    /*
        Other Optional filed
        public static Specification<BlogPostEntity> publishedAfter(OffsetDateTime startDate) {
            return (root, query, builder) -> startDate == null ? builder.conjunction() : builder.greaterThanOrEqualTo(root.get("createdAt"), startDate);
        }
    */

    public static Specification<BlogPostEntity> publishedWithinRange(OffsetDateTime startDate, OffsetDateTime endDate) {
        return (root, query, builder) -> {
            if (startDate == null && endDate == null) {
                return builder.conjunction();
            } else if (startDate != null && endDate != null) {
                return builder.between(root.get("createdAt"), startDate, endDate);
            } else if (startDate != null) {
                return builder.greaterThanOrEqualTo(root.get("createdAt"), startDate);
            } else {
                return builder.lessThanOrEqualTo(root.get("createdAt"), endDate);
            }
        };
    }
}
