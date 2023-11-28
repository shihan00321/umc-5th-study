package umc.spring.service.memberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.repository.MemberRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberRepository memberRepository;
    @Override
    public boolean existById(Long id) {
        return memberRepository.existsById(id);
    }
}
