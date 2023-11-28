package umc.spring.web.dto;

import lombok.Getter;
import umc.spring.validation.annotation.ExistMember;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ReviewRequestDTO {
    @Getter
    public static class WriteReviewDTO {
        @ExistMember
        private Long memberId;
        @Size(max = 100)
        private String description;
        @NotNull
        private double grade;

    }
}
