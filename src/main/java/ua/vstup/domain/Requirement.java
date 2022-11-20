package ua.vstup.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Requirement {
    private Integer id;

    @NotNull(message = "requirementInfo.firstSubject.null.exception.message")
    private Subject first_subject;

    @NotNull(message = "requirementInfo.secondSubject.null.exception.message")
    private Subject second_subject;

    @NotNull(message = "requirementInfo.thirdSubject.null.exception.message")
    private Subject third_subject;

    public List<Subject> getSubjectList() { return Arrays.asList(first_subject, second_subject, third_subject);}

    public Subject getSubjectBySubjectName(SubjectName subjectName){
        return getSubjectList().stream()
                .filter(subject -> subject.getName().equals(subjectName))
                .findFirst()
                .orElse(null);
    }

}
