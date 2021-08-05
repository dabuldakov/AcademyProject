package practice.department;

import practice.NotFoundException;
import practice.person.PersonDto;

import javax.validation.Valid;
import java.util.List;

public interface DepartmentService {
    public DepartmentDto find(int id);
    public List<DepartmentDto> findAll();
    public void update(@Valid DepartmentDto dto) throws NotFoundException;
    public DepartmentDto create(@Valid DepartmentDto dto);
    public void delete(@Valid DepartmentDto dto);
}
