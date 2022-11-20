package ua.vstup.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "entrant")
public class EntrantEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private SchoolEntity schoolEntity;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private RoleEntity role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "requirement_id")
    private RequirementEntity requirementEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "certification_id")
    private DocEntity certificationDocEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id")
    private DocEntity passportDocEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id")
    private DocEntity photoDocEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assessment_id")
    private DocEntity assessmentDocEntity;

    @Column(name = "active", nullable = false)
    private Boolean active;
}
