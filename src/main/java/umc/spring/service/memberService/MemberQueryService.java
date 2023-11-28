package umc.spring.service.memberService;

import umc.spring.domain.Member;

import java.util.Optional;

public interface MemberQueryService {
    Optional<Member> findMember(Long id);
    boolean existById(Long id);
}
