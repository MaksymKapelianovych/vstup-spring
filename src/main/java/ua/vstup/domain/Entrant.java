package ua.vstup.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entrant entrant = (Entrant) o;
        return Objects.equals(id, entrant.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
