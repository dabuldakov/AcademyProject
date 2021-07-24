package practice;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import practice.department.Department;
import practice.department.DepartmentDAO;
import practice.person.Person;
import practice.person.PersonDAO;
import practice.utils.SpringConfig;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {

    static PersonDAO personDAO;
    static DepartmentDAO departmentDAO;

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        departmentDAO = context.getBean("departmentDAO", DepartmentDAO.class);
        personDAO = context.getBean("personDAO", PersonDAO.class);

//        savePerson();
        deletePerson(13);


    }
    public static void findPerson(int id){
        System.out.println("Found person: " + personDAO.findPerson(id));
    }

    public static void savePerson(){
        Person person = new Person();
        person.setFirstName("Olya");
        person.setSecondName("Frolova");
        try {
            person.setBirthday(DATE_FORMAT.parse("1983-02-14"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Department department = new Department();
        department.setId(1);
        department.setName("Java developers department");
        person.setDepartment(department);
        boolean b = Main.personDAO.savePerson(person);
        System.out.println("Saved person: " + b);
    }

    public static void updatePerson(){
        Person person = new Person();
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
        Person personUpdated = Main.personDAO.updatePerson(person);
        System.out.println("Updated person: " + personUpdated);
    }

    public static void deletePerson(int id){
        Person person = new Person();
        person.setId(id);
        boolean b = personDAO.deletePerson(person);
        System.out.println("Deleted person: " + b);
    }
}
