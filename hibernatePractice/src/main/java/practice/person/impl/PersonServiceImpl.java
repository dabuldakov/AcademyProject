package practice.person.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import practice.department.Department;
import practice.mapper.Mapper;
import practice.person.*;
import practice.util.Constants;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository repository;

    @Autowired
    @Qualifier("personDaoJpa")
    private PersonDao dao;

    @Autowired
    Mapper mapper;


    public PersonDto find(int id) {
        return mapper.run(dao.find(id), PersonDto.class);
    }

    public List<PersonDto> findAll() {
        return repository.findAll().stream()
                .map(x -> mapper.run(x, PersonDto.class))
                .collect(Collectors.toList());
    }

    public void update(PersonDto personDTO) {
        dao.update(mapper.run(personDTO, Person.class));
    }

    public PersonDto create(PersonDto personDTO) {
        Person person = dao.create(mapper.run(personDTO, Person.class));
        return mapper.run(person, PersonDto.class);
    }

    public void delete(PersonDto personDto) {
        dao.delete(mapper.run(personDto, Person.class));
    }

    public void createPersons() {
        Department department = new Department();
        department.setId(2);

        ArrayList<Person> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            person.setFirstName("Oleg " + i);
            person.setSecondName("Pchelintsev");
            try {
                person.setBirthday(Constants.DATE_FORMAT.parse("1983-02-14"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            person.setDepartment(department);
            list.add(person);
        }
        dao.createList(list);
        list.forEach(System.out::println);
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
