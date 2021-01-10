package ua.vstup.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class School {
    private Integer id;

    @NotNull(message = "school.name_ua.exception.message")
    private String nameUa;

    @NotNull(message = "school.name_en.exception.message")
    private String nameEn;

    @NotNull(message = "school.city_ua.exception.message")
    private String cityUa;

    @NotNull(message = "school.city_en.exception.message")
    private String cityEn;

    @NotNull(message = "school.region.null.exception.message")
    private Region region;

    public String getNameByLocale(String locale){
        if(locale == null){
            return nameEn;
        }
        switch (locale){
            case "ua":
                return nameUa;
            default:
                return nameEn;
        }
    }

    public String getCityByLocale(String locale){
        if(locale == null){
            return cityEn;
        }
        switch (locale){
            case "ua":
                return cityUa;
            default:
                return cityEn;
        }
    }
}
