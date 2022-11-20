package ua.vstup.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Objects;

@Getter
@Setter
public class Faculty {
    private Integer id;

    @NotNull(message = "faculty.name_ua.exception.message")
    private String name_ua;

    @NotNull(message = "faculty.name_en.exception.message")
    private String name_en;

    @PositiveOrZero(message = "faculty.max_budget_place.not.positive.exception.message")
    @NotNull(message = "faculty.maxBudgetPlace.exception.message")
    private Integer max_budget_place;

    @Positive(message = "faculty.max_place.not.positive.exception.message")
    @NotNull(message = "faculty.max_place.exception.message")
    private Integer max_place;

    @NotNull(message = "faculty.requirementInfo.null.exception.message")
    private Requirement requirement;

    private Boolean active;

    public String getNameByLocale(String locale){
        if(locale == null){
            return name_en;
        }
        switch (locale){
            case "ua":
                return name_ua;
            default:
                return name_en;
        }
    }

    public void decreasePlace() {
        max_place--;
    }

    public void decreaseBudgetPlace() {
        max_budget_place--;
        decreasePlace();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return Objects.equals(id, faculty.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
