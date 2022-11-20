package ua.vstup.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
public class UserInfo {
    private Integer id;

    @NotNull(message = "user.name.null.exception.message")
    private String name;

    @NotNull(message = "user.email.null.exception.message")
    private String email;

    @NotNull(message = "user.password.null.exception.message")
    private String password;

    private Integer school_id;
    private Requirement requirement;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo entrant = (UserInfo) o;
        return Objects.equals(id, entrant.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
