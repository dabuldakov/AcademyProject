package practice.department.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import practice.department.Department;
import practice.department.DepartmentDao;
import practice.department.DepartmentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class DepartmentDaoJpa implements DepartmentDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    EntityManager em;

    @Autowired
    private DepartmentRepository repository;

    @Override
    public Department find(int id) {
        return em.find(Department.class, id);
    }

    @Override
    public List<Department> findAll() {
        return repository.findAll();
    }

    @Override
    public void update(Department department) {
        em.merge(department);
    }

    @Override
    public ArrayList<Department> updateList(ArrayList<Department> list) {
        return null;
    }

    @Override
    public Department create(Department department) {
        em.persist(department);
        return department;
    }

    @Override
    public void createList(ArrayList<Department> list) {

    }

    @Override
    public void delete(Department department) {
        Department department1 = em.find(Department.class, department.getId());
        em.remove(department1);
    }

    @Override
    public ArrayList<Department> deleteList(ArrayList<Department> list) {
        return null;
    }
}
