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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "first_subject_id", nullable = false)
    private SubjectEntity firstSubjectEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "second_subject_id", nullable = false)
    private SubjectEntity secondSubjectEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "third_subject_id", nullable = false)
    private SubjectEntity thirdSubjectEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fourth_subject_id")
    private SubjectEntity fourthSubjectEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fifth_subject_id")
    private SubjectEntity fifthSubjectEntity;
}
