package ua.vstup.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "faculty")
public class FacultyEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name_en", nullable = false, unique = true)
    private String nameEn;

    @Column(name = "name_ua", nullable = false, unique = true)
    private String nameUa;

    @Column(name = "max_budget_place", nullable = false)
    private Integer maxBudgetPlace;

    @Column(name = "max_place", nullable = false)
    private Integer maxPlace;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "requirement_id", unique = true)
    private RequirementEntity requirementEntity;

    @Column(name = "active", nullable = false)
    private Boolean active;
}
