package practice.department;

import org.springframework.stereotype.Component;
import practice.NotFoundException;
import practice.document.Document;

import java.util.ArrayList;
import java.util.List;

@Component
public interface DepartmentDao {
    public Department find(int id);
    public List<Department> findAll();

    public void update(Department department) throws NotFoundException;
    public Department create(Department department);
    public void delete(Department department);

    public void createList(ArrayList<Department> list);
    public ArrayList<Department> updateList(ArrayList<Department> list);
    public ArrayList<Department> deleteList(ArrayList<Department> list);
}
