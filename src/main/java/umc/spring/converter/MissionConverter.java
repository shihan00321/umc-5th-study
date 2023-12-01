package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;

import umc.spring.domain.mapping.MissionParticipation;
import umc.spring.web.dto.MissionParticipationRequestDTO;
import umc.spring.web.dto.MissionParticipationResponseDTO;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {
    public static Mission toMission(MissionRequestDTO.RegisterMission registerMissionDTO) {
        return Mission.builder()
                .deadline(registerMissionDTO.getDeadline())
                .description(registerMissionDTO.getDescription())
                .reward(registerMissionDTO.getReward())
                .missionParticipationList(new ArrayList<>())
                .build();
    }

    public static MissionResponseDTO.RegisterMissionResult toRegisterMissionResult(Mission mission) {
        return MissionResponseDTO.RegisterMissionResult
                .builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
    public static MissionParticipation toMissionParticipation(MissionParticipationRequestDTO.ChallengeMission challengeMissionDTO) {
        return MissionParticipation.builder()
                .status(challengeMissionDTO.getMissionStatus())
                .build();
    }

    public static MissionParticipationResponseDTO.ChallengeMissionResultDTO toChallengeMissionResultDTO(MissionParticipation missionParticipation) {
        return MissionParticipationResponseDTO.ChallengeMissionResultDTO
                .builder()
                .missionParticipationId(missionParticipation.getId())
                .build();
    }

    public static MissionResponseDTO.MissionPreviewDTO missionPreviewDTO(Mission mission) {
        return MissionResponseDTO.MissionPreviewDTO.builder()
                .restaurantName(mission.getRestaurant().getName())
                .restaurantCategoryName(mission.getRestaurant().getCategory().getName())
                .description(mission.getDescription())
                .reward(mission.getReward())
                .createdAt(mission.getCreatedAt().toLocalDate())
                .build();
    }

    public static MissionResponseDTO.MissionPreviewListDTO missionPreviewListDTO(Page<Mission> missionList) {
        List<MissionResponseDTO.MissionPreviewDTO> missionPreviewListDTO = missionList.stream()
                .map(MissionConverter::missionPreviewDTO)
                .collect(Collectors.toList());

        return MissionResponseDTO.MissionPreviewListDTO
                .builder()
                .isFirst(missionList.isFirst())
                .isLast(missionList.isLast())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionPreviewListDTO.size())
                .missionList(missionPreviewListDTO)
                .build();
    }
}
