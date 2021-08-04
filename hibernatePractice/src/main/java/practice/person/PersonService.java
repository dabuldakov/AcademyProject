package practice.person;

import practice.NotFoundException;
import practice.person.exception.PersonException;
import practice.person.exception.PersonIdException;

import java.util.ArrayList;
import java.util.List;

public interface PersonService {
    public PersonDto find(int id) throws NotFoundException;
    public List<PersonDto> findAll();
    public void update(PersonDto personDTO) throws PersonException, NotFoundException;
    public PersonDto create(PersonDto personDTO) throws NotFoundException;
    public void delete(PersonDto personDTO) throws PersonIdException;
    public List<PersonDto> createList(ArrayList<PersonDto> list) throws NotFoundException;
}
