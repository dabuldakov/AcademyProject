package practice.person.service;

import practice.person.model.PersonDto;

import java.util.List;

public interface PersonService {
    PersonDto find(int id);
    List<PersonDto> findAll();
    void update(PersonDto personDTO);
    PersonDto create(PersonDto personDTO);
    void delete(PersonDto personDTO);
    List<PersonDto> createList(List<PersonDto> list);
    public List<PersonDto> getAllByFirstName(String firstName);
}
