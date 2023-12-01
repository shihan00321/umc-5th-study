package umc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Member;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MissionParticipation;

public interface MissionParticipationRepository extends JpaRepository<MissionParticipation, Long> {
    Page<MissionParticipation> findAllByMemberAndStatus(Member member, MissionStatus status, PageRequest pageRequest);
}
