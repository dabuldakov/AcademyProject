package practice.language;

import practice.valid.CapitalLetter;

import javax.validation.constraints.NotBlank;

public class LanguageDto {
    private int id;

    @NotBlank
    @CapitalLetter
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
