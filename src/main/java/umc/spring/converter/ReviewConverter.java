package umc.spring.converter;

import umc.spring.domain.Review;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReviewConverter {
    public static ReviewResponseDTO.RegisterReviewResult toRegisterReviewResult (Review review) {
        return ReviewResponseDTO.RegisterReviewResult
                .builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(ReviewRequestDTO.WriteReviewDTO reviewDTO) {
        return Review
                .builder()
                .description(reviewDTO.getDescription())
                .grade(reviewDTO.getGrade())
                .build();
    }
}
