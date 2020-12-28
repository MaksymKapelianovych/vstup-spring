package ua.vstup.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class School {
    private Integer id;

    @NotNull(message = "school.name_ua.exception.message")
    private String name_ua;

    @NotNull(message = "school.name_en.exception.message")
    private String name_en;

    @NotNull(message = "school.city_ua.exception.message")
    private String city_ua;

    @NotNull(message = "school.city_en.exception.message")
    private String city_en;

    @NotNull(message = "school.region.null.exception.message")
    private Region region;
}
