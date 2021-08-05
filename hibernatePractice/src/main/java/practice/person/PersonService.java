package practice.person;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public interface PersonService {
    PersonDto find(int id);
    List<PersonDto> findAll();
    void update(@Valid PersonDto personDTO);
    PersonDto create(@Valid PersonDto personDTO);
    void delete(@Valid PersonDto personDTO);
    List<PersonDto> createList(ArrayList<PersonDto> list);
}
