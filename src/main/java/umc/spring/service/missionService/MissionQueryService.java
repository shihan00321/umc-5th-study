package umc.spring.service.missionService;

import umc.spring.domain.Mission;

import java.util.Optional;

public interface MissionQueryService {
    Optional<Mission> findMission(Long missionId);
}
