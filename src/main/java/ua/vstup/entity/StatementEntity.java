package ua.vstup.entity;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "statement")
public class StatementEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "finalized", nullable = false)
    private Boolean finalized;
}
