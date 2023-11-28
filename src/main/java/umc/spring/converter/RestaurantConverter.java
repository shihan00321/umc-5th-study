package umc.spring.converter;

import umc.spring.domain.Category;
import umc.spring.domain.Restaurant;
import umc.spring.domain.Town;
import umc.spring.web.dto.RestaurantRequestDTO;
import umc.spring.web.dto.RestaurantResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class RestaurantConverter {
    public static RestaurantResponseDTO.RegisterResultDTO toRegisterResultDTO(Restaurant restaurant) {
        return RestaurantResponseDTO.RegisterResultDTO.builder()
                .restaurantId(restaurant.getId())
                .name(restaurant.getName())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Restaurant toRestaurant(RestaurantRequestDTO.RegisterDTO registerDTO, Category category, Town town) {
        return Restaurant.builder()
                .name(registerDTO.getName())
                .category(category)
                .town(town)
                .address(registerDTO.getAddress())
                .missionList(new ArrayList<>())
                .build();
    }
}
