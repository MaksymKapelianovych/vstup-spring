package ua.vstup.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class EntrantInfo {
    private final Integer id;

    @NotNull(message = "entrant.name.null.exception.message")
    private final String name;

    @NotNull(message = "entrant.email.null.exception.message")
    private final String email;

    private final School school;
    private final Role role;
    private final RequirementInfo requirementInfo;

    private boolean passed;

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
