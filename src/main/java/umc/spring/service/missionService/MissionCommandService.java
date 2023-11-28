package umc.spring.service.missionService;

import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MissionParticipation;
import umc.spring.web.dto.MissionParticipationRequestDTO;

public interface MissionCommandService {
    MissionParticipation challengeMission(MissionParticipationRequestDTO.ChallengeMission missionDTO, Long missionId);
}
