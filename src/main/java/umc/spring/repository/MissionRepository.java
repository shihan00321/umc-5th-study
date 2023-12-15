package umc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Mission;
import umc.spring.domain.Restaurant;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    Page<Mission> findAllByRestaurant(Restaurant restaurant, PageRequest pageRequest);
}
