package practice.department;

import practice.valid.annotations.CapitalLetter;

import javax.validation.constraints.NotBlank;

public class DepartmentDto {

    private int id;

    @NotBlank
    @CapitalLetter
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
