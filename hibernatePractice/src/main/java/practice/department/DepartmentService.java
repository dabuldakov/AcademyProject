package practice.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DepartmentService {

    private final DepartmentDAO departmentDAO;

    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentService(@Qualifier("departmentDAOJpa")DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    private void insertDepartments() {
        int count = 0;
        ArrayList<Department> list = new ArrayList<>();

        while (count < 10) {
            Department department = new Department();
            department.setName("new department " + count);
            list.add(department);
            count++;
        }
        departmentDAO.createList(list);
        list.forEach(System.out::println);
    }



    private void deleteDepartments() {
        ArrayList<Department> list = new ArrayList<>();
        for (int i = 14; i < 30; i++) {
            Department department = new Department();
            department.setId(i);
            list.add(department);
        }
        departmentDAO.deleteList(list);
    }
}
