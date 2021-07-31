package practice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
//        PersonService personService = context.getBean("personService", PersonService.class);
//        DepartmentService departmentService = context.getBean("departmentService", DepartmentService.class);
//        Service documentService = context.getBean("documentService", DocumentService.class);
    }

}
