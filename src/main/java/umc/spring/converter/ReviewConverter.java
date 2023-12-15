package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static ReviewResponseDTO.ReviewPreviewDTO reviewPreviewDTO(Review review) {
        return ReviewResponseDTO.ReviewPreviewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .grade(review.getGrade())
                .createdAt(review.getCreatedAt().toLocalDate())
                .description(review.getDescription())
                .build();
    }

    public static ReviewResponseDTO.ReviewPreviewListDTO reviewPreviewListDTO(Page<Review> reviewList) {
        List<ReviewResponseDTO.ReviewPreviewDTO> reviewPreviewListDTO = reviewList.stream()
                .map(ReviewConverter::reviewPreviewDTO)
                .collect(Collectors.toList());

        return ReviewResponseDTO.ReviewPreviewListDTO
                .builder()
                .isFirst(reviewList.isFirst())
                .isLast(reviewList.isLast())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreviewListDTO.size())
                .reviewList(reviewPreviewListDTO)
                .build();
    }
}
