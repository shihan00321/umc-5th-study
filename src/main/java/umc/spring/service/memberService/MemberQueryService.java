package umc.spring.service.memberService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;

import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MissionParticipation;

import umc.spring.domain.Review;


import java.util.Optional;

public interface MemberQueryService {
    Optional<Member> findMember(Long id);
    boolean existById(Long id);

    Page<MissionParticipation> getMyMissionList(Long memberId, MissionStatus status, Integer page);

    Page<Review> getMyReviewList(Long memberId, Integer page);

}
