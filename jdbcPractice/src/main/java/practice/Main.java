package practice;

import practice.department.Department;
import practice.department.DepartmentDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import practice.person.Person;
import practice.person.PersonDAO;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws ParseException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
//        insertPersons();
//        insertDepartments();
//        deletePersons();
//        deleteDepartments();
        updatePersons();


    }

    private static void updatePersons() throws ParseException {
        Department department = new Department();
        department.setId(1);
        ArrayList<Person> list = new ArrayList<>();
        for (int i = 65; i < 70; i++) {
            Person person = new Person();
            person.setId(i);
            person.setFirstName("Oleg updated again " + i);
            person.setSecondName("Pchelintsev");
            person.setBirthday(Constants.DATE_FORMAT.parse("1983-02-14"));
            person.setDepartment(department);
            list.add(person);
        }
        PersonDAO personDAO = new PersonDAO();
        personDAO.updatePersons(list).forEach(System.out::println);
    }

    private static void insertPersons() throws ParseException {
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
        PersonDAO personDAO = new PersonDAO();
        personDAO.insertPersons(list);
        list.forEach(System.out::println);
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
        list.forEach(System.out::println);
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
