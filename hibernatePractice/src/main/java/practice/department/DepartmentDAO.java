package practice.department;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public interface DepartmentDAO {
    public Department find(int id);

    public boolean update(Department department);

    public ArrayList<Department> updateList(ArrayList<Department> list);

    public Department create(Department department);

    public void createList(ArrayList<Department> list);

    public boolean delete(Department department);

    public ArrayList<Department> deleteList(ArrayList<Department> list);
}
