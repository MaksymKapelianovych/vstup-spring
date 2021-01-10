package ua.vstup.domain;

import lombok.EqualsAndHashCode;
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
    private String nameUa;

    @NotNull(message = "faculty.name_en.exception.message")
    private String nameEn;

    @PositiveOrZero(message = "faculty.maxBudgetPlace.not.positive.exception.message")
    @NotNull(message = "faculty.maxBudgetPlace.exception.message")
    private Integer maxBudgetPlace;

    @Positive(message = "faculty.maxPlace.not.positive.exception.message")
    @NotNull(message = "faculty.maxPlace.exception.message")
    private Integer maxPlace;

    @NotNull(message = "faculty.requirementInfo.null.exception.message")
    private Requirement requirement;

    private Boolean active;

    public String getNameByLocale(String locale){
        if(locale == null){
            return nameEn;
        }
        switch (locale){
            case "ua":
                return nameUa;
            default:
                return nameEn;
        }
    }

    public void decreasePlace() {
        maxPlace--;
    }

    public void decreaseBudgetPlace() {
        maxBudgetPlace--;
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
