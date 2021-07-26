package practice;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import practice.department.DepartmentService;
import practice.person.PersonService;

public class Main {


    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        PersonService personService = context.getBean("personService", PersonService.class);
        DepartmentService departmentService = context.getBean("departmentService", DepartmentService.class);
        personService.createPerson();
        //personService.createPersons();
        //personService.updatePerson();
        //personService.deletePerson();
        //personService.getAllByFirstName().forEach(System.out::println);

    }


}
