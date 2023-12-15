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
import umc.spring.converter.MemberConverter;

import umc.spring.converter.MissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MissionParticipation;

import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Review;

import umc.spring.service.memberService.MemberCommandService;
import umc.spring.service.memberService.MemberQueryService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;

import umc.spring.web.dto.MissionParticipationResponseDTO;
import umc.spring.web.dto.MissionResponseDTO;

import umc.spring.web.dto.ReviewResponseDTO;


import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/users")
public class MemberRestController {
    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @PostMapping
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDTO joinDTO) {
        Member member = memberCommandService.joinMember(joinDTO);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    
    @Operation(summary = "특정 회원이 작성한 리뷰 목록 조회 API",description = "특정 회원의 리뷰 목록을 조회하는 API로 페이징을 포함한다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
            @Parameter(name = "status", description = "미션 상태, SUCCESS, CHALLENGING, FAILURE, PAYMENTS 중 하나"),
    })
    @GetMapping("/{memberId}/missions")
    public ApiResponse<MissionParticipationResponseDTO.MyMissionListDTO> getMyReviewList(@PathVariable @ExistMember Long memberId,
                                                                                         @RequestParam @CheckPage Integer page,@RequestParam MissionStatus status) {
        Page<MissionParticipation> missionParticipationList = memberQueryService.getMyMissionList(memberId, status, page);
        return ApiResponse.onSuccess(MissionConverter.toGetMyMissionListDTO(missionParticipationList));
    }


    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
            @Parameter(name = "status", description = "미션 상태, SUCCESS, CHALLENGING, FAILURE, PAYMENTS 중 하나"),
    })
    @Operation(summary = "특정 회원이 진행 중인 미션 목록 조회 API",description = "특정 회원이 진행 중인 미션 목록을 조회하는 API로 페이징을 포함한다. query String 으로 page 번호를 주세요")
    @GetMapping("/{memberId}/reviews")
    public ApiResponse<ReviewResponseDTO.ReviewPreviewListDTO> getMyReviewList(@PathVariable @ExistMember Long memberId, @RequestParam @CheckPage Integer page) {
        Page<Review> reviewList = memberQueryService.getMyReviewList(memberId, page);
        return ApiResponse.onSuccess(ReviewConverter.reviewPreviewListDTO(reviewList));
    }

}
