package ua.vstup.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
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

    private Boolean is_active;

    private Integer unique_school_id;

    public String getNameByLocale(String locale){
        if(locale == null){
            return name_en;
        }
        switch (locale){
            case "ua":
                return name_ua;
            default:
                return name_en;
        }
    }

    public String getCityByLocale(String locale){
        if(locale == null){
            return city_en;
        }
        switch (locale){
            case "ua":
                return city_ua;
            default:
                return city_en;
        }
    }
}
