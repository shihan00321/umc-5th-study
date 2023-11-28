package umc.spring.service.RestaurantService;

import umc.spring.domain.Mission;
import umc.spring.domain.Restaurant;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.RestaurantRequestDTO;

public interface RestaurantCommandService {
    Restaurant registerRestaurant(RestaurantRequestDTO.RegisterDTO registerDTO);
    Mission registerMission(MissionRequestDTO.RegisterMission registerMissionDTO, Long restaurantId);
}
