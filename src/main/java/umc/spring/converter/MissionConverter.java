package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

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
}
