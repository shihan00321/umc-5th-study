package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.mapping.MissionParticipation;
import umc.spring.service.missionService.MissionCommandService;
import umc.spring.validation.annotation.ExistMission;
import umc.spring.web.dto.MissionParticipationRequestDTO;
import umc.spring.web.dto.MissionParticipationResponseDTO;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/missions")
public class MissionRestController {

    private final MissionCommandService missionCommandService;
    @PostMapping("/{missionId}/participation")
    public ApiResponse<MissionParticipationResponseDTO.ChallengeMissionResultDTO> challenge(
            @RequestBody @Valid MissionParticipationRequestDTO.ChallengeMission challengeMissionDTO,
            @PathVariable @ExistMission Long missionId) {
        MissionParticipation missionParticipation = missionCommandService.challengeMission(challengeMissionDTO, missionId);
        return ApiResponse.onSuccess(MissionConverter.toChallengeMissionResultDTO(missionParticipation));
    }
}
