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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name_en", nullable = false)
    private String name_en;

    @Column(name = "name_ua", nullable = false)
    private String name_ua;

    @Column(name = "maxBudgetPlace", nullable = false)
    private Integer maxBudgetPlace;

    @Column(name = "maxPlace", nullable = false)
    private Integer maxPlace;

    @OneToOne
    @JoinColumn(name = "requirement_id")
    private RequirementEntity requirementEntity;

    @Column(name = "active", nullable = false)
    private Boolean active;
}
