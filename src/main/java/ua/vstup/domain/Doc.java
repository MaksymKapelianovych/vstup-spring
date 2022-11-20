package ua.vstup.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Doc {

    private Integer id;

    @NotNull
    private DocType type;

    private String path;

}
