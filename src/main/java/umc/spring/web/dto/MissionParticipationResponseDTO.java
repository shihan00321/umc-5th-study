package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionParticipationResponseDTO {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChallengeMissionResultDTO {
        private Long missionParticipationId;
        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyMissionDTO {
        private String restaurantName;
        private String restaurantCategoryName;
        private String description;
        private Integer reward;
        private LocalDate deadline;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyMissionListDTO {
        List<MyMissionDTO> missionList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }
}
