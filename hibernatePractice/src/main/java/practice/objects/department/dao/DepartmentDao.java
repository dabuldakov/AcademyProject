package practice.objects.department.dao;

import org.springframework.stereotype.Component;
import practice.objects.department.Department;

import java.util.ArrayList;
import java.util.List;

@Component
public interface DepartmentDao {
    Department find(int id);
    List<Department> findAll();

    void update(Department department);
    Department create(Department department);
    void delete(Department department);

    void createList(ArrayList<Department> list);
    ArrayList<Department> updateList(ArrayList<Department> list);
    ArrayList<Department> deleteList(ArrayList<Department> list);
}
