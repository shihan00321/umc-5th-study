package umc.spring.service.memberService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import umc.spring.domain.Member;

import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MissionParticipation;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionParticipationRepository;

import umc.spring.domain.Review;
import umc.spring.repository.ReviewRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;

    private final MissionParticipationRepository missionParticipationRepository;

    private final ReviewRepository reviewRepository;

    @Override
    public Optional<Member> findMember(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public boolean existById(Long id) {
        return memberRepository.existsById(id);
    }

    @Override
    public Page<MissionParticipation> getMyMissionList(Long memberId, MissionStatus status, Integer page) {
        Member member = memberRepository.findById(memberId).get();
        return missionParticipationRepository.findAllByMemberAndStatus(member, status, PageRequest.of(page, 10));
    }
    @Override
    public Page<Review> getMyReviewList(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId).get();
        return reviewRepository.findAllByMember(member, PageRequest.of(page, 10));
    }
}
