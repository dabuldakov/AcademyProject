package practice.person.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import practice.exception.IdException;
import practice.exception.NotFoundException;
import practice.mapper.Mapper;
import practice.person.model.Person;
import practice.person.dao.PersonDao;
import practice.person.model.PersonDto;

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

    public void update(PersonDto personDTO) throws NotFoundException {
        if (personDTO.getId() == 0)
            throw new IdException("Person id is empty.");
        dao.update(mapper.convert(personDTO, Person.class));
    }

    public PersonDto create(PersonDto personDto) throws NotFoundException {
        Person person = dao.create(mapper.convert(personDto, Person.class));
        return find(person.getId());
    }

    public void delete(PersonDto personDto) throws IdException {
        if (personDto.getId() == 0)
            throw new IdException("Person id: " + personDto.getId());
        dao.delete(mapper.convert(personDto, Person.class));
    }

    public List<PersonDto> createList(List<PersonDto> list){
        List<PersonDto> personDtoList = new ArrayList<>();
        for (PersonDto personDto : list) {
                PersonDto createdPerson = create(personDto);
                personDtoList.add(createdPerson);
        }
        return personDtoList;
    }

    public List<PersonDto> getAllByFirstName(String firstName) {
        return dao.getAllByFirstName(firstName).stream()
                .map(x -> mapper.convert(x, PersonDto.class))
                .collect(Collectors.toList());
    }
}
