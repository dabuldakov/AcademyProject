package practice;

import practice.department.Department;
import practice.department.DepartmentDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import practice.person.Person;
import practice.person.PersonDAO;

import java.text.ParseException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws ParseException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        PersonDAO personDAO = new PersonDAO();
        Department department = new Department();
        department.setName("New name");
        department.setId(13);
        DepartmentDAO departmentDAO = new DepartmentDAO();
        departmentDAO.updateDepartment(department);

        Person person = new Person();
        person.setFirstName("Oleg");
        person.setSecondName("Pchelintsev");
        person.setBirthday(Constants.DATE_FORMAT.parse("1983-02-14"));
        person.setDepartment(department);

        Person person1 = new Person();
        person1.setFirstName("Oleg 1");
        person1.setSecondName("Pchelintsev");
        person1.setBirthday(Constants.DATE_FORMAT.parse("1983-03-14"));
        person1.setDepartment(department);

        Person person2 = new Person();
        person2.setFirstName("Oleg 2");
        person2.setSecondName("Pchelintsev");
        person2.setBirthday(Constants.DATE_FORMAT.parse("1983-04-14"));
        person2.setDepartment(department);

        Person person3 = new Person();
        person3.setFirstName("Oleg 3");
        person3.setSecondName("Pchelintsev");
        person3.setBirthday(Constants.DATE_FORMAT.parse("1983-05-14"));
        person3.setDepartment(department);

        ArrayList<Person> list = new ArrayList<>();
        list.add(person);
        list.add(person1);
        list.add(person2);
        list.add(person3);
        personDAO.createPersons(list);
        System.out.println(list);
    }
}
