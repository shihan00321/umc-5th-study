package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.enums.Gender;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MemberConverter {
    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member) {
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberID(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDTO joinDTO) {
        Gender gender = null;
        switch (joinDTO.getGender()) {
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }
        return Member.builder()
                .address(joinDTO.getAddress())
                .email(joinDTO.getEmail())
                .gender(gender)
                .name(joinDTO.getName())
                .birth(String.format("%04d-%02d-%02d", joinDTO.getBirthYear(), joinDTO.getBirthMonth(), joinDTO.getBirthDay()))
                .memberCategoryList(new ArrayList<>())
                .build();
    }
}
