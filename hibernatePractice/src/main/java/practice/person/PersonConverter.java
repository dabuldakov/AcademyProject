package practice.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import practice.department.DepartmentConverter;
import practice.document.DocumentConverter;
import practice.language.LanguageConverter;

import java.util.stream.Collectors;

@Component
public class PersonConverter {

    @Autowired
    DocumentConverter documentConverter;

    @Autowired
    DepartmentConverter departmentConverter;

    @Autowired
    LanguageConverter languageConverter;

    public Person toPerson(PersonDto personDTO){
        Person person = new Person();
        person.setId(personDTO.getId());
        person.setFirstName(personDTO.getFirstName());
        person.setSecondName(personDTO.getSecondName());
        person.setBirthday(personDTO.getBirthday());
        if(personDTO.getDocument() != null)
        person.setDocument(documentConverter.toDocument(personDTO.getDocument()));
        if(personDTO.getDepartment() != null)
        person.setDepartment(departmentConverter.toDepartment(personDTO.getDepartment()));
        if(personDTO.getLanguage() != null && !personDTO.getLanguage().isEmpty())
        person.setLanguage(personDTO.getLanguage().stream()
                .map(x -> languageConverter.toLanguage(x))
                .collect(Collectors.toList()));
        return person;
    }

    public PersonDto toPersonDTO(Person person){
        PersonDto personDTO = new PersonDto();
        personDTO.setId(person.getId());
        personDTO.setFirstName(person.getFirstName());
        personDTO.setSecondName(person.getSecondName());
        personDTO.setBirthday(person.getBirthday());
        if(person.getDocument() != null)
        personDTO.setDocument(documentConverter.toDocumentDto(person.getDocument()));
        if(person.getDepartment() != null)
        personDTO.setDepartment(departmentConverter.toDepartmentDto(person.getDepartment()));
        if(person.getLanguage() != null && !person.getLanguage().isEmpty())
        personDTO.setLanguage(person.getLanguage().stream()
                .map(x -> languageConverter.toLanguageDto(x))
                .collect(Collectors.toList()));
        return personDTO;
    }
}
