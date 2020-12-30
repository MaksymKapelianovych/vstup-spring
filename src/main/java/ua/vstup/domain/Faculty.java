package ua.vstup.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class Faculty {
    private Integer id;

    @NotNull(message = "faculty.name_ua.exception.message")
    private String name_ua;

    @NotNull(message = "faculty.name_en.exception.message")
    private String name_en;

    @PositiveOrZero(message = "faculty.maxBudgetPlace.not.positive.exception.message")
    @NotNull(message = "faculty.maxBudgetPlace.exception.message")
    private Integer maxBudgetPlace;

    @Positive(message = "faculty.maxPlace.not.positive.exception.message")
    @NotNull(message = "faculty.maxPlace.exception.message")
    private Integer maxPlace;

    @NotNull(message = "faculty.requirementInfo.null.exception.message")
    private Requirement requirement;

    private Boolean active;

    public void decreasePlace() {
        maxPlace--;
    }

    public void decreaseBudgetPlace() {
        maxBudgetPlace--;
        decreasePlace();
    }
}
