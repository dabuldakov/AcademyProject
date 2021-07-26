package practice.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.Constants;
import practice.department.Department;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private PersonRepository personRepository;
    private final PersonDAO personDAO;

    public PersonService(@Qualifier("personDAOJpa")PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public void findPerson(int id){
        System.out.println("Found person: " + personDAO.find(id));
    }

    public void createPerson(){
        Person person = new Person();
        person.setFirstName("Olya 5");
        person.setSecondName("Frolova 5");
        try {
            person.setBirthday(DATE_FORMAT.parse("1983-02-14"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Department department = new Department();
        department.setId(1);
        department.setName("Java developers department");
        person.setDepartment(department);
        personDAO.create(person);
        System.out.println("Saved person: " + personDAO.find(person.getId()));
        //System.out.println("by name: " + personRepository.findByFirstName("Natasha"));
    }

    private void createPersons() throws ParseException {
        Department department = new Department();
        department.setId(2);

        ArrayList<Person> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            person.setFirstName("Oleg " + i);
            person.setSecondName("Pchelintsev");
            person.setBirthday(Constants.DATE_FORMAT.parse("1983-02-14"));
            person.setDepartment(department);
            list.add(person);
        }
        personDAO.createList(list);
        list.forEach(System.out::println);
    }

    public void updatePerson(){
        Person person = new Person();
        person.setId(81);
        person.setFirstName("Updated firstName");
        person.setSecondName("Updated secondName");
        try {
            person.setBirthday(DATE_FORMAT.parse("1983-02-14"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Department department = new Department();
        department.setId(1);
        department.setName("Java developers department");
        person.setDepartment(department);
        boolean update = personDAO.update(person);
        System.out.println("Updated: " + update);
        System.out.println("Updated person: " + personRepository.findById(person.getId()));
    }

    private void updatePersons() throws ParseException {
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

    public void deletePerson(){
        Person person = new Person();
        person.setId(84);
        boolean delete = personDAO.delete(person);
        System.out.println(delete);
        System.out.println("Deleted person: " + person);
    }

    private void deletePersons() {
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
