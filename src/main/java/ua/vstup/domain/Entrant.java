package ua.vstup.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Entrant {
    private Integer id;

    @NotNull(message = "entrant.name.null.exception.message")
    private String name;

    @NotNull(message = "entrant.email.null.exception.message")
    private String email;

    @NotNull(message = "entrant.password.null.exception.message")
    private String password;

    private School school;
    private Role role;
    private Requirement requirement;

    private boolean active;
    private boolean passed;

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
