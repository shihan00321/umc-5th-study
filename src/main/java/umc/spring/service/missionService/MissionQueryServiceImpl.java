package umc.spring.service.missionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Mission;
import umc.spring.domain.Restaurant;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.RestaurantRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {
    private final MissionRepository missionRepository;
    private final RestaurantRepository restaurantRepository;
    @Override
    public Optional<Mission> findMission(Long missionId) {
        return missionRepository.findById(missionId);
    }

    @Override
    public Page<Mission> getMissionList(Long restaurantId, Integer page) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        return missionRepository.findAllByRestaurant(restaurant, PageRequest.of(page, 10));
    }
}
