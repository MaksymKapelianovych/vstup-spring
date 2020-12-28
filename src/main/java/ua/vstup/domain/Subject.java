package ua.vstup.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Subject {
    private Integer id;

    @NotNull(message = "subject.name.null.exception.message")
    private SubjectName name;

    @Min(value = 100, message = "subject.rate.less.than.100.exception.message")
    @Max(value = 200, message = "subject.rate.greater.than.200.exception.message")
    @NotNull(message = "subject.rate.null.exception.message")
    private Integer rate;
}
