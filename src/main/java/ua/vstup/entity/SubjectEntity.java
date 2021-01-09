package ua.vstup.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "subject")
public class SubjectEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private SubjectNameEntity subjectNameEntity;

    @Column(name = "rate", nullable = false)
    private Integer rate;
}
