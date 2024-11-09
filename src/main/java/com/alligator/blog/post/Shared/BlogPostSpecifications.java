package com.alligator.blog.post.Shared;

import com.alligator.blog.post.Entities.BlogPostEntity;
import com.alligator.blog.post.Shared.Enums.BlogType;
import org.springframework.data.jpa.domain.Specification;

import java.time.OffsetDateTime;

public class BlogPostSpecifications {

    public static Specification<BlogPostEntity> hasMerchantName(String merchantName) {
        return (root, query, builder) -> builder.equal(root.get("merchantName"), merchantName);
    }

    // Perform search on the title & subtitle fields
    public static Specification<BlogPostEntity> searchOnTitleOrSubtitle(String keyword) {
        return (root, query, builder) -> {
            if (keyword == null || keyword.isEmpty()) {
                return builder.conjunction();
            }
            // `OR` condition to match keyword in either title or subtitle
            return builder.or(
                    builder.like(root.get("title"), "%" + keyword + "%"),
                    builder.like(root.get("subTitle"), "%" + keyword + "%")
            );
        };
    }

    /*
        Other Optional filed
        public static Specification<BlogPostEntity> publishedAfter(OffsetDateTime startDate) {
            return (root, query, builder) -> startDate == null ? builder.conjunction() : builder.greaterThanOrEqualTo(root.get("createdAt"), startDate);
        }
    */

    // Filter posts published within a date range
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

    // Filter by blog type
    public static Specification<BlogPostEntity> hasType(BlogType type) {
        return (root, query, builder) ->  type == null ? builder.conjunction() : builder.equal(root.get("type"), type);
    }
}
