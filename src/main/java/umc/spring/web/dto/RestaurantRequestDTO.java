package umc.spring.web.dto;

import lombok.Getter;

public class RestaurantRequestDTO {
    @Getter
    public static class RegisterDTO {
        private String name;
        private Long categoryId;
        private Long townId;
        private String address;
    }
}
