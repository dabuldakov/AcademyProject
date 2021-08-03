package practice.person;

import practice.person.exception.PersonException;
import practice.person.exception.PersonIdException;
import practice.person.exception.PersonNotFoundException;

import java.util.ArrayList;
import java.util.List;

public interface PersonService {
    public PersonDto find(int id) throws PersonNotFoundException;
    public List<PersonDto> findAll();
    public void update(PersonDto personDTO) throws PersonException;
    public PersonDto create(PersonDto personDTO) throws PersonNotFoundException;
    public void delete(PersonDto personDTO) throws PersonIdException;
    public List<PersonDto> createList(ArrayList<PersonDto> list) throws PersonNotFoundException;
}
