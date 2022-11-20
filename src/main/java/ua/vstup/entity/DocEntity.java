package ua.vstup.entity;

import lombok.Getter;
import lombok.Setter;
import ua.vstup.domain.DocType;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "doc")
public class DocEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private DocTypeEntity type;

    @Column(name = "path", nullable = false)
    private String path;
}
