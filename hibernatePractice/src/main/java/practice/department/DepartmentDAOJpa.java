package practice.department;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Component
@Transactional
public class DepartmentDAOJpa implements DepartmentDAO {

    @PersistenceContext(unitName = "entityManagerFactory")
    EntityManager entityManager;

    @Override
    public Department find(int id) {
        return null;
    }

    @Override
    public boolean update(Department department) {
        return false;
    }

    @Override
    public ArrayList<Department> updateList(ArrayList<Department> list) {
        return null;
    }

    @Override
    public Department create(Department department) {
        entityManager.persist(department);
        return department;
    }

    @Override
    public void createList(ArrayList<Department> list) {

    }

    @Override
    public boolean delete(Department department) {
        return false;
    }

    @Override
    public ArrayList<Department> deleteList(ArrayList<Department> list) {
        return null;
    }
}
