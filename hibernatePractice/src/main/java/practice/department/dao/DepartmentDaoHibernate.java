package practice.department.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import practice.department.model.Department;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentDaoHibernate implements DepartmentDao {
    SessionFactory sessionFactory;

    public DepartmentDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Department find(int id) {
        return null;
    }

    @Override
    public List<Department> findAll() {
        return null;
    }

    @Override
    public void update(Department department) {

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
    public void delete(Department person) {

    }

    @Override
    public ArrayList<Department> deleteList(ArrayList<Department> list) {
        return null;
    }
}
