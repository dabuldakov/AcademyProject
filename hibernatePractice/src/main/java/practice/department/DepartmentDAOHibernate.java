package practice.department;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DepartmentDAOHibernate implements DepartmentDAO {
    SessionFactory sessionFactory;

    public DepartmentDAOHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


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
        return department;
    }

    @Override
    public void createList(ArrayList<Department> list) {

    }

    @Override
    public boolean delete(Department person) {
        return false;
    }

    @Override
    public ArrayList<Department> deleteList(ArrayList<Department> list) {
        return null;
    }
}
