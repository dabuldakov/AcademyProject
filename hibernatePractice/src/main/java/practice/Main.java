package practice;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) throws ParseException {
        PersonDAO personDAO = new PersonDAO();
        Person person = new Person();

        //Find person
//        person = personDAO.findPerson(15);
//        System.out.println(person);

        //Update person
//        person.setFirstName("Oleg aka Hibernate");
//        Person person1 = personDAO.updatePerson(person);
//        System.out.println(person1);

        //Delete person
//        person.setId(60);
//        boolean b = personDAO.deletePerson(person);
//        System.out.println(b);

        //Save person
        person.setFirstName("Olya");
        person.setSecondName("Frolova");
        person.setBirthday(DATE_FORMAT.parse("1983-02-14"));
        Department department = new Department();
        department.setId(1);
        department.setName("Java developers department");
        person.setDepartment(department);
        boolean b = personDAO.savePerson(person);
        System.out.println(b);



    }
}
