package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MissionParticipation;
import umc.spring.web.dto.MissionParticipationRequestDTO;
import umc.spring.web.dto.MissionParticipationResponseDTO;

import java.time.LocalDateTime;

public class MissionConverter {
    public static MissionParticipation toMissionParticipation(MissionParticipationRequestDTO.ChallengeMission challengeMissionDTO) {
        return MissionParticipation.builder()
                .status(challengeMissionDTO.getMissionStatus())
                .build();
    }

    public static MissionParticipationResponseDTO.ChallengeMissionResultDTO toChallengeMissionResultDTO(MissionParticipation missionParticipation) {
        return MissionParticipationResponseDTO.ChallengeMissionResultDTO
                .builder()
                .missionParticipationId(missionParticipation.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
