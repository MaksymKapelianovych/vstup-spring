package ua.vstup.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
@Setter
public class Entrant {
    private Integer id;

    @NotEmpty(message = "entrant.name.null.exception.message")
    @Size(min = 5, max = 250)
    private String name;

    @NotEmpty(message = "entrant.email.null.exception.message")
    private String email;

    @NotEmpty(message = "entrant.password.null.exception.message")
    @Size(min = 5, max = 30)
    private String password;

    private School school;
    private Role role;
    private Requirement requirement;

//    private String certificatePath;
//    private String passportPath;
//    private String photoPath;
//    private String assessmentPath;

    private Doc certificate;
    private Doc passport;
    private Doc photo;
    private Doc assessment;

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
