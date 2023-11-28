package umc.spring.service.RestaurantService;

import umc.spring.domain.Restaurant;
import umc.spring.web.dto.RestaurantRequestDTO;

public interface RestaurantCommandService {
    Restaurant registerRestaurant(RestaurantRequestDTO.RegisterDTO registerDTO);
}
