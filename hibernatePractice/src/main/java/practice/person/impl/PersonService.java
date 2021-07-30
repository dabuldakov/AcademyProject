package practice.person.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import practice.Constants;
import practice.department.Department;
import practice.person.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService implements practice.person.PersonService {

    @Autowired
    private PersonRepository repository;

    @Autowired
    @Qualifier("personDaoJpa")
    private PersonDao dao;

    @Autowired
    private PersonConverter converter;



    public PersonDto find(int id){
        return converter.toPersonDTO(dao.find(id));
    }

    public List<PersonDto> findAll(){
        return repository.findAll().stream()
                .map(x -> converter.toPersonDTO(x))
                .collect(Collectors.toList());
    }

    public void update(PersonDto personDTO){
        dao.update(converter.toPerson(personDTO));
    }

    public PersonDto create(PersonDto personDTO){
        Person person = dao.create(converter.toPerson(personDTO));
        return converter.toPersonDTO(person);
    }

    public void delete(PersonDto personDTO){
        dao.delete(converter.toPerson(personDTO));
    }

    public void createPersons(){
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

    public List<Person> getAllByFirstName(String firstName){
        return dao.getAllByFirstName(firstName);
    }
}
