package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.RestaurantConverter;
import umc.spring.domain.Restaurant;
import umc.spring.service.RestaurantCommandService;
import umc.spring.web.dto.RestaurantRequestDTO;
import umc.spring.web.dto.RestaurantResponseDTO;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantCommandService restaurantCommandService;

    @PostMapping("/")
    public ApiResponse<RestaurantResponseDTO.RegisterResultDTO> register(@RequestBody @Valid RestaurantRequestDTO.RegisterDTO registerDTO) {
        Restaurant restaurant = restaurantCommandService.registerRestaurant(registerDTO);
        return ApiResponse.onSuccess(RestaurantConverter.toRegisterResultDTO(restaurant));
    }
}
