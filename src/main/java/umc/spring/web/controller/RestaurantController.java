package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

import umc.spring.service.missionService.MissionQueryService;

import umc.spring.service.RestaurantService.RestaurantQueryService;

import umc.spring.validation.annotation.CheckPage;
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

    private final MissionQueryService missionQueryService;

    private final RestaurantQueryService restaurantQueryService;


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


    @Operation(summary = "특정 가게의 미션 목록 조회 API",description = "특정 가게의 미션들의 목록을 조회하는 API로 페이징을 포함한다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "restaurantId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    @GetMapping("/{restaurantId}/missions")
    public ApiResponse<MissionResponseDTO.MissionPreviewListDTO> getMissionList(@ExistRestaurant @PathVariable Long restaurantId, @CheckPage @RequestParam(name = "page") Integer page) {
        Page<Mission> missionList = missionQueryService.getMissionList(restaurantId, page);
        System.out.println("missionList = " + missionList.getClass());
        return ApiResponse.onSuccess(MissionConverter.missionPreviewListDTO(missionList));
    }

    @GetMapping("/{restaurantId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "restaurantId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreviewListDTO> getReviewList(@ExistRestaurant @PathVariable Long restaurantId, @CheckPage @RequestParam(name = "page") Integer page) {
        Page<Review> reviewList = restaurantQueryService.getReviewList(restaurantId, page);
        return ApiResponse.onSuccess(ReviewConverter.reviewPreviewListDTO(reviewList));
    }
}
