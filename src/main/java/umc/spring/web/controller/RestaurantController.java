package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.RestaurantConverter;
import umc.spring.domain.Mission;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Restaurant;
import umc.spring.domain.Review;
import umc.spring.service.RestaurantService.RestaurantCommandService;
import umc.spring.validation.annotation.ExistRestaurant;

import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;
import umc.spring.web.dto.RestaurantRequestDTO;
import umc.spring.web.dto.RestaurantResponseDTO;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

import javax.validation.Valid;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantCommandService restaurantCommandService;

    @PostMapping("/")
    public ApiResponse<RestaurantResponseDTO.RegisterResultDTO> register(@RequestBody @Valid RestaurantRequestDTO.RegisterDTO registerDTO) {
        Restaurant restaurant = restaurantCommandService.registerRestaurant(registerDTO);
        return ApiResponse.onSuccess(RestaurantConverter.toRegisterResultDTO(restaurant));
    }


    @PostMapping("/{restaurantId}/missions")
    public ApiResponse<MissionResponseDTO.RegisterMissionResult> registerMission(@RequestBody @Valid MissionRequestDTO.RegisterMission registerMissionDTO, @PathVariable @ExistRestaurant Long restaurantId) {
        Mission mission = restaurantCommandService.registerMission(registerMissionDTO, restaurantId);
        return ApiResponse.onSuccess(MissionConverter.toRegisterMissionResult(mission));
    }
    @PostMapping("/{restaurantId}/reviews")
    public ApiResponse<ReviewResponseDTO.RegisterReviewResult> writeReview(@RequestBody @Valid ReviewRequestDTO.WriteReviewDTO reviewDTO, @PathVariable @ExistRestaurant Long restaurantId) {
        Review review = restaurantCommandService.registerReview(reviewDTO, restaurantId);
        return ApiResponse.onSuccess(ReviewConverter.toRegisterReviewResult(review));

    }
}
