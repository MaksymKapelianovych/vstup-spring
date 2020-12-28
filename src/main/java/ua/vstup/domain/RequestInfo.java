package ua.vstup.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Builder
@Getter
public class RequestInfo {
    private final Integer id;

    @NotNull(message = "requestInfo.entrantInfo.null.exception.message")
    private final EntrantInfo entrant;

    @NotNull(message = "requestInfo.faculty.null.exception.message")
    private final Faculty faculty;

    @NotNull(message = "requestInfo.firstSubject.null.exception.message")
    private final Subject firstSubject;

    @NotNull(message = "requestInfo.secondSubject.null.exception.message")
    private final Subject secondSubject;

    @NotNull(message = "requestInfo.thirdSubject.null.exception.message")
    private final Subject thirdSubject;

    private final Integer priority;
    private final State state;

    public Integer getRate() {
        return getFirstSubject().getRate() +
                getSecondSubject().getRate() +
                getThirdSubject().getRate();
    }

}
