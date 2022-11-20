package ua.vstup.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class Request {
    private Integer id;

    @NotNull(message = "requestInfo.entrantInfo.null.exception.message")
    private Entrant entrant;

    @NotNull(message = "requestInfo.faculty.null.exception.message")
    private Faculty faculty;

    @NotNull(message = "requestInfo.firstSubject.null.exception.message")
    private Subject first_subject;

    @NotNull(message = "requestInfo.secondSubject.null.exception.message")
    private Subject second_subject;

    @NotNull(message = "requestInfo.thirdSubject.null.exception.message")
    private Subject third_subject;

    private Statement statement;
    private Integer priority;
    private RequestState request_state;

    public Integer getRate() {
        return getFirst_subject().getRate() +
                getSecond_subject().getRate() +
                getThird_subject().getRate();
    }

}
