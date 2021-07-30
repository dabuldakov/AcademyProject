package practice.person;

import org.springframework.stereotype.Component;

@Component
public class PersonConverter {

    public Person toPerson(PersonDto personDTO){
        Person person = new Person();
        person.setId(personDTO.getId());
        person.setFirstName(personDTO.getFirstName());
        person.setSecondName(personDTO.getSecondName());
        person.setBirthday(personDTO.getBirthday());
        person.setDocument(personDTO.getDocument());
        person.setDepartment(personDTO.getDepartment());
        person.setLanguage(personDTO.getLanguage());
        return person;
    }

    public PersonDto toPersonDTO(Person person){
        PersonDto personDTO = new PersonDto();
        personDTO.setId(person.getId());
        personDTO.setFirstName(person.getFirstName());
        personDTO.setSecondName(person.getSecondName());
        personDTO.setBirthday(person.getBirthday());
        personDTO.setDocument(person.getDocument());
        personDTO.setDepartment(person.getDepartment());
        personDTO.setLanguage(person.getLanguage());
        return personDTO;
    }
}
