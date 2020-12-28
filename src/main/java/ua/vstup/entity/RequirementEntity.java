package ua.vstup.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "requirement")
public class RequirementEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "first_subject_id", nullable = false)
    private SubjectEntity firstSubjectEntity;

    @OneToOne
    @JoinColumn(name = "second_subject_id", nullable = false)
    private SubjectEntity secondSubjectEntity;

    @OneToOne
    @JoinColumn(name = "third_subject_id", nullable = false)
    private SubjectEntity thirdSubjectEntity;

    @ManyToOne
    @JoinColumn(name = "fourth_subject_id")
    private SubjectEntity fourthSubjectEntity;

    @ManyToOne
    @JoinColumn(name = "fifth_subject_id")
    private SubjectEntity fifthSubjectEntity;
}
