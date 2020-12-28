package ua.vstup.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class Request {
    private Integer id;

    @NotNull(message = "request.entrantId.null.exception.message")
    private Integer entrantId;

    @NotNull(message = "request.facultyId.null.exception.message")
    private Integer facultyId;

    @NotNull(message = "request.firstSubjectId.null.exception.message")
    private Integer firstSubjectId;

    @NotNull(message = "request.secondSubjectId.null.exception.message")
    private Integer secondSubjectId;

    @NotNull(message = "request.thirdSubjectId.null.exception.message")
    private Integer thirdSubjectId;

    private Integer statementId;
    private Integer priority;
    private State state;

}
