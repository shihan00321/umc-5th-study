package umc.spring.service.missionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MissionParticipation;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionParticipationRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.web.dto.MissionParticipationRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionCommandServiceImpl implements MissionCommandService {
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final MissionParticipationRepository missionParticipationRepository;
    @Transactional
    @Override
    public MissionParticipation challengeMission(MissionParticipationRequestDTO.ChallengeMission missionPartDTO, Long missionId) {
        MissionParticipation missionParticipation = MissionConverter.toMissionParticipation(missionPartDTO);

        Mission mission = missionRepository.findById(missionId).get();
        missionParticipation.setMission(mission);

        Member member = memberRepository.findById(missionPartDTO.getMemberId()).get();
        missionParticipation.setMember(member);

        return missionParticipationRepository.save(missionParticipation);
    }
}
