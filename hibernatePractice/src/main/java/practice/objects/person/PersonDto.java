package practice.objects.person;

import practice.objects.department.DepartmentDto;
import practice.objects.document.DocumentDto;
import practice.objects.language.LanguageDto;
import practice.validation.annotations.CapitalLetter;
import practice.validation.annotations.Letters;
import practice.validation.validator.Marker;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.List;

public class PersonDto {

    @Null(groups = Marker.OnCreate.class)
    @NotNull(groups = Marker.OnUpdate.class)
    private Integer id;

    @NotBlank
    @CapitalLetter
    @Letters
    private String firstName;

    @NotBlank
    @CapitalLetter
    @Letters
    private String secondName;

    @NotNull
    private Date birthday;

    private DepartmentDto department;
    private DocumentDto document;
    private List<LanguageDto> language;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public DepartmentDto getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDto department) {
        this.department = department;
    }

    public DocumentDto getDocument() {
        return document;
    }

    public void setDocument(DocumentDto document) {
        this.document = document;
    }

    public List<LanguageDto> getLanguage() {
        return language;
    }

    public void setLanguage(List<LanguageDto> language) {
        this.language = language;
    }
}
