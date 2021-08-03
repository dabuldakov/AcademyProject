package practice.person;

import java.util.List;

public interface PersonService {
    public PersonDto find(int id);
    public List<PersonDto> findAll();
    public void update(PersonDto personDTO) throws PersonException;
    public PersonDto create(PersonDto personDTO) throws PersonException;
    public void delete(PersonDto personDTO);
}
