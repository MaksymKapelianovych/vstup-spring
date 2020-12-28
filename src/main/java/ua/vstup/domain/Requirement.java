package ua.vstup.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Requirement {
    private Integer id;

    @NotNull(message = "requirement.firstSubjectId.null.exception.message")
    private Integer firstSubjectId;

    @NotNull(message = "requirement.secondSubjectId.null.exception.message")
    private Integer secondSubjectId;

    @NotNull(message = "requirement.thirdSubjectId.null.exception.message")
    private Integer thirdSubjectId;

    private Integer fourthSubjectId;
    private Integer fifthSubjectId;

}
