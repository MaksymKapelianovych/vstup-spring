package ua.vstup.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "school")
public class SchoolEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name_en", nullable = false)
    private String name_en;

    @Column(name = "name_ua", nullable = false)
    private String name_ua;

    @Column(name = "city_en", nullable = false)
    private String city_en;

    @Column(name = "city_ua", nullable = false)
    private String city_ua;

    @Enumerated(EnumType.STRING)
    @Column(name = "region", nullable = false)
    private RegionEntity regionEntity;
}
