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
//        insertPersons();
//        insertDepartments();
//        deletePersons();
        deleteDepartments();


    }

    private static void insertDepartments(){
        int count = 0;
        ArrayList<Department> list = new ArrayList<>();

        while (count < 10) {
            Department department = new Department();
            department.setName("new department " + count);
            list.add(department);
            count++;
        }

        DepartmentDAO departmentDAO = new DepartmentDAO();
        departmentDAO.createDepartments(list);
        System.out.println(list);
    }

    private static void insertPersons() throws ParseException {
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
        personDAO.insertPersons(list);
        System.out.println(list);
    }

    private static void deletePersons(){
        PersonDAO personDAO = new PersonDAO();
        ArrayList<Person> list = new ArrayList<>();

        for (int i = 16; i < 60; i++) {
            Person person = new Person();
            person.setId(i);
            list.add(person);
        }
        boolean b = personDAO.deletePersons(list);
        System.out.println(b);
    }
    
    private static void deleteDepartments(){
        DepartmentDAO departmentDAO = new DepartmentDAO();
        ArrayList<Department> list = new ArrayList<>();
        for (int i = 14; i < 30; i++) {
            Department department = new Department();
            department.setId(i);
            list.add(department);
        }
        boolean b = departmentDAO.deleteDepartments(list);
        System.out.println(b);
    }
}
