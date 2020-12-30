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
    private Subject firstSubject;

    @NotNull(message = "requestInfo.secondSubject.null.exception.message")
    private Subject secondSubject;

    @NotNull(message = "requestInfo.thirdSubject.null.exception.message")
    private Subject thirdSubject;

    private Statement statement;
    private Integer priority;
    private RequestState requestState;

    public Integer getRate() {
        return getFirstSubject().getRate() +
                getSecondSubject().getRate() +
                getThirdSubject().getRate();
    }

}
