package practice.department;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public interface DepartmentDao {
    public Department find(int id);

    public void update(Department department);

    public ArrayList<Department> updateList(ArrayList<Department> list);

    public Department create(Department department);

    public void createList(ArrayList<Department> list);

    public void delete(Department department);

    public ArrayList<Department> deleteList(ArrayList<Department> list);
}
