package ua.vstup.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

@Builder
@Getter
public class RequirementInfo {

    @NotNull(message = "requirementInfo.firstSubject.null.exception.message")
    private final Subject firstSubject;

    @NotNull(message = "requirementInfo.secondSubject.null.exception.message")
    private final Subject secondSubject;

    @NotNull(message = "requirementInfo.thirdSubject.null.exception.message")
    private final Subject thirdSubject;

    private final Subject fourthSubject;
    private final Subject fifthSubject;

    public List<Subject> getSubjectList() { return Arrays.asList(firstSubject, secondSubject, thirdSubject, fourthSubject, fifthSubject);}

    public Subject getSubjectBySubjectName(SubjectName subjectName){
        return getSubjectList().stream()
                .filter(subject -> subject.getName().equals(subjectName))
                .findFirst()
                .orElse(null);
    }
}
