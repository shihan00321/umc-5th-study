package umc.spring.web.dto;

import lombok.Getter;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.validation.annotation.ExistMember;

import javax.validation.constraints.NotNull;

public class MissionParticipationRequestDTO {
    @Getter
    public static class ChallengeMission {
        @ExistMember
        private Long memberId;
        @NotNull
        private MissionStatus missionStatus;
    }
}
