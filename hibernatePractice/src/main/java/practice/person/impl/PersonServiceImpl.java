package practice.person.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import practice.NotFoundException;
import practice.department.Department;
import practice.mapper.Mapper;
import practice.person.*;
import practice.person.exception.PersonException;
import practice.person.exception.PersonIdException;
import practice.util.Constants;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    @Qualifier("personDaoJpa")
    private PersonDao dao;

    @Autowired
    Mapper mapper;


    public PersonDto find(int id) throws NotFoundException {
        return mapper.run(dao.find(id), PersonDto.class);
    }

    public List<PersonDto> findAll() {
        return dao.findAll().stream()
                .map(x -> mapper.run(x, PersonDto.class))
                .collect(Collectors.toList());
    }

    public void update(PersonDto personDTO) throws PersonException, NotFoundException {
        if (personDTO.getId() == 0)
            throw new PersonIdException("Person id: " + personDTO.getId());
        dao.update(mapper.run(personDTO, Person.class));
    }

    public PersonDto create(PersonDto personDto) throws NotFoundException {
        Person person = dao.create(mapper.run(personDto, Person.class));
        return find(person.getId());
    }

    public void delete(PersonDto personDto) throws PersonIdException {
        if (personDto.getId() == 0)
            throw new PersonIdException("Person id: " + personDto.getId());
        dao.delete(mapper.run(personDto, Person.class));
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

    public void updatePersons() throws ParseException {
        Department department = new Department();
        department.setId(1);
        ArrayList<Person> list = new ArrayList<>();
        for (int i = 65; i < 70; i++) {
            Person person = new Person();
            person.setId(i);
            person.setFirstName("check spring " + i);
            person.setSecondName("Pchelintsev");
            person.setBirthday(Constants.DATE_FORMAT.parse("1983-02-14"));
            person.setDepartment(department);
            list.add(person);
        }
        dao.updateList(list);
    }

    public void deletePersons() {
        ArrayList<Person> list = new ArrayList<>();

        for (int i = 16; i < 60; i++) {
            Person person = new Person();
            person.setId(i);
            list.add(person);
        }
        dao.deleteList(list);
    }

    public List<Person> getAllByFirstName(String firstName) {
        return dao.getAllByFirstName(firstName);
    }
}
