package ua.vstup.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "request")
public class RequestEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "entrant_id", nullable = false)
    private EntrantEntity entrantEntity;

    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    private FacultyEntity facultyEntity;

    @ManyToOne
    @JoinColumn(name = "first_subject_id", nullable = false)
    private SubjectEntity firstSubjectEntity;

    @ManyToOne
    @JoinColumn(name = "second_subject_id", nullable = false)
    private SubjectEntity secondSubjectEntity;

    @ManyToOne
    @JoinColumn(name = "third_subject_id", nullable = false)
    private SubjectEntity thirdSubjectEntity;

    @ManyToOne
    @JoinColumn(name = "statement_id")
    private StatementEntity statementEntity;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private RequestStateEntity requestStateEntity;


    @Column(name = "priority", nullable = false, length = 1)
    private Integer priority;
}
