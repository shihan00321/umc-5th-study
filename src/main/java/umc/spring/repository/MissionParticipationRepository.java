package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.mapping.MissionParticipation;

public interface MissionParticipationRepository extends JpaRepository<MissionParticipation, Long> {
}
