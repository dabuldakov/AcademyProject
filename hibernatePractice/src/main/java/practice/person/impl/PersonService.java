package practice.person.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import practice.Constants;
import practice.department.Department;
import practice.person.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService implements practice.person.PersonService {

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private PersonRepository personRepository;
    private final PersonDao personDAO;
    @Autowired
    private PersonConverter personConverter;

    public PersonService(@Qualifier("personDaoJpa") PersonDao personDAO) {
        this.personDAO = personDAO;
    }

    public PersonDto find(int id){
        return personConverter.toPersonDTO(personDAO.find(id));
    }

    public List<PersonDto> findAll(){
        return personRepository.findAll().stream()
                .map(x -> personConverter.toPersonDTO(x))
                .collect(Collectors.toList());
    }

    public boolean update(PersonDto personDTO){
        return personDAO.update(personConverter.toPerson(personDTO));
    }

    public PersonDto create(PersonDto personDTO){
        Person person = personDAO.create(personConverter.toPerson(personDTO));
        return personConverter.toPersonDTO(person);
    }

    public boolean delete(PersonDto personDTO){
        return personDAO.delete(personConverter.toPerson(personDTO));
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
        personDAO.createList(list);
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
        personDAO.updateList(list);
    }

    public void deletePersons() {
        ArrayList<Person> list = new ArrayList<>();

        for (int i = 16; i < 60; i++) {
            Person person = new Person();
            person.setId(i);
            list.add(person);
        }
        personDAO.deleteList(list);
    }

    public List<Person> getAllByFirstName(){
        return personDAO.getAllByFirstName();
    }
}
