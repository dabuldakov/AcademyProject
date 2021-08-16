package practice.department.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.department.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
