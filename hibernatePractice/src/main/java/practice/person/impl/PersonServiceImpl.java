package practice.person.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import practice.department.Department;
import practice.exception.IdException;
import practice.exception.NotFoundException;
import practice.mapper.Mapper;
import practice.person.Person;
import practice.person.PersonDao;
import practice.person.PersonDto;
import practice.person.PersonService;
import practice.util.Constants;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    @Qualifier("personDaoJpa")
    private PersonDao dao;

    @Autowired
    Mapper mapper;


    public PersonDto find(int id) throws NotFoundException {
        return mapper.convert(dao.find(id), PersonDto.class);
    }

    public List<PersonDto> findAll() {
        return dao.findAll().stream()
                .map(x -> mapper.convert(x, PersonDto.class))
                .collect(Collectors.toList());
    }

    public void update(@Valid PersonDto personDTO) throws NotFoundException {
        if (personDTO.getId() == 0)
            throw new IdException("Person id: " + personDTO.getId());
        dao.update(mapper.convert(personDTO, Person.class));
    }

    public PersonDto create(@Valid PersonDto personDto) throws NotFoundException {
        Person person = dao.create(mapper.convert(personDto, Person.class));
        return find(person.getId());
    }

    public void delete(@Valid PersonDto personDto) throws IdException {
        if (personDto.getId() == 0)
            throw new IdException("Person id: " + personDto.getId());
        dao.delete(mapper.convert(personDto, Person.class));
    }

    public List<PersonDto> createList(ArrayList<PersonDto> list){
        List<PersonDto> personDtoList = new ArrayList<>();
        for (PersonDto personDto : list) {
            try{
                PersonDto createdPerson = create(personDto);
                personDtoList.add(createdPerson);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return personDtoList;
    }

    public List<Person> getAllByFirstName(String firstName) {
        return dao.getAllByFirstName(firstName);
    }
}
