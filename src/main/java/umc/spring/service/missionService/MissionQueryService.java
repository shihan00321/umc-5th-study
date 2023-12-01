package umc.spring.service.missionService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;

import java.util.Optional;

public interface MissionQueryService {
    Optional<Mission> findMission(Long missionId);
    Page<Mission> getMissionList(Long restaurantId, Integer page);
}
