package umc.spring.web.dto;

import lombok.Getter;
import umc.spring.validation.annotation.ExistCategories;
import umc.spring.validation.annotation.ExistCategory;
import umc.spring.validation.annotation.ExistTown;

import javax.validation.constraints.NotNull;

public class RestaurantRequestDTO {
    @Getter
    public static class RegisterDTO {
        @NotNull
        private String name;
        @ExistCategory
        private Long categoryId;
        @ExistTown
        private Long townId;
        @NotNull
        private String address;
    }
}
