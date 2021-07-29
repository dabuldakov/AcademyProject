package practice.person;

import java.util.List;

public interface PersonService {
    public PersonDto find(int id);
    public boolean update(PersonDto personDTO);
    public PersonDto create(PersonDto personDTO);
    public boolean delete(PersonDto personDTO);
    public List<PersonDto> findAll();
}
