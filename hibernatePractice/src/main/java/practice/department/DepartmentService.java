package practice.department;

import practice.NotFoundException;
import practice.person.PersonDto;

import java.util.List;

public interface DepartmentService {
    public DepartmentDto find(int id);
    public List<DepartmentDto> findAll();
    public void update(DepartmentDto dto) throws NotFoundException;
    public DepartmentDto create(DepartmentDto dto);
    public void delete(DepartmentDto dto);
}
