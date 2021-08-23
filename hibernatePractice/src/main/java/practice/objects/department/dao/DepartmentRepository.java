package practice.objects.department.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.objects.department.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
