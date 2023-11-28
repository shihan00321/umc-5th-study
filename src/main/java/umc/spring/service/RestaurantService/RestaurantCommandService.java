package umc.spring.service.RestaurantService;

import umc.spring.domain.Mission;
import umc.spring.domain.Restaurant;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.domain.Review;
import umc.spring.web.dto.RestaurantRequestDTO;
import umc.spring.web.dto.ReviewRequestDTO;

public interface RestaurantCommandService {
    Restaurant registerRestaurant(RestaurantRequestDTO.RegisterDTO registerDTO);

    Mission registerMission(MissionRequestDTO.RegisterMission registerMissionDTO, Long restaurantId);

    Review registerReview(ReviewRequestDTO.WriteReviewDTO reviewDTO, Long restaurantId);

}
