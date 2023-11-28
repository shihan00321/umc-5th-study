package umc.spring.service.memberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.FoodCategoryHandler;
import umc.spring.converter.MemberCategoryConverter;
import umc.spring.converter.MemberConverter;
import umc.spring.domain.Category;
import umc.spring.domain.Member;
import umc.spring.domain.mapping.MemberCategory;
import umc.spring.repository.CategoryRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.web.dto.MemberRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCommandServiceImpl implements MemberCommandService {
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDTO joinDTO) {
        Member member = MemberConverter.toMember(joinDTO);
        List<Category> categoryList = joinDTO.getMemberCategoryList().stream()
                .map(categoryId -> {
                    return categoryRepository.findById(categoryId).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());
        List<MemberCategory> memberCategoryList = MemberCategoryConverter.toMemberCategoryList(categoryList);
        memberCategoryList.forEach(memberCategory -> {
            memberCategory.setMember(member);
        });
        return memberRepository.save(member);
    }
}
