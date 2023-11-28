package umc.spring.service.missionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Mission;
import umc.spring.repository.MissionRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {
    private final MissionRepository missionRepository;
    @Override
    public Optional<Mission> findMission(Long missionId) {
        return missionRepository.findById(missionId);
    }
}
