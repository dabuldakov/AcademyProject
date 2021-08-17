package practice.language.model;

import practice.valid.annotations.CapitalLetter;
import practice.valid.annotations.Letters;
import practice.valid.validator.Marker;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class LanguageDto {


    @Null(groups = Marker.OnCreate.class)
    @NotNull(groups = Marker.OnUpdate.class)
    private Integer id;

    @NotBlank
    @CapitalLetter
    @Letters
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
