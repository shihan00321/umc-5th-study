package umc.spring.web.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Range;
import umc.spring.validation.annotation.FutureDateTime;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class MissionRequestDTO {
    @Getter
    public static class RegisterMission {
        @NotNull
        private String description;
        @FutureDateTime
        private LocalDateTime deadline;
        @Range(max = 10000, min = 0)
        private Integer reward;
    }
}
