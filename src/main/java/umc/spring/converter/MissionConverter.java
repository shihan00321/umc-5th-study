package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;

import umc.spring.domain.Restaurant;
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


    public static MissionParticipationResponseDTO.MyMissionDTO toGetMyMissionDTO(MissionParticipation missionParticipation) {
        //todo fetch join으로 한번에 가져오게 수정
        Mission mission = missionParticipation.getMission();
        Restaurant restaurant = mission.getRestaurant();

        return MissionParticipationResponseDTO.MyMissionDTO
                .builder()
                .restaurantName(restaurant.getName())
                .restaurantCategoryName(restaurant.getCategory().getName())
                .description(mission.getDescription())
                .reward(mission.getReward())
                .deadline(mission.getDeadline().toLocalDate())
                .build();
    }

    public static MissionParticipationResponseDTO.MyMissionListDTO toGetMyMissionListDTO(Page<MissionParticipation> missionParticipationList) {
        List<MissionParticipationResponseDTO.MyMissionDTO> missionList = missionParticipationList.stream()
                .map(MissionConverter::toGetMyMissionDTO).collect(Collectors.toList());

        return MissionParticipationResponseDTO.MyMissionListDTO
                .builder()
                .missionList(missionList)
                .isFirst(missionParticipationList.isFirst())
                .isLast(missionParticipationList.isLast())
                .totalPage(missionParticipationList.getTotalPages())
                .totalElements(missionParticipationList.getTotalElements())
                .listSize(missionList.size())

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
