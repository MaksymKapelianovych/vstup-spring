package ua.vstup.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Builder
@Getter
public class FacultyInfo {
    private final Integer id;

    @NotNull(message = "faculty.name_ua.exception.message")
    private final String name_ua;

    @NotNull(message = "faculty.name_en.exception.message")
    private final String name_en;

    @PositiveOrZero(message = "faculty.maxBudgetPlace.not.positive.exception.message")
    @NotNull(message = "faculty.maxBudgetPlace.exception.message")
    private final Integer maxBudgetPlace;

    @Positive(message = "faculty.maxPlace.not.positive.exception.message")
    @NotNull(message = "faculty.maxPlace.exception.message")
    private final Integer maxPlace;

    @NotNull(message = "faculty.requirementInfo.null.exception.message")
    private final RequirementInfo requirementInfo;

    private final Boolean active;
}
