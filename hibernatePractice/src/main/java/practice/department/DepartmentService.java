package practice.department;

import practice.exception.NotFoundException;

import javax.validation.Valid;
import java.util.List;

public interface DepartmentService {
    DepartmentDto find(int id);
    List<DepartmentDto> findAll();
    void update(@Valid DepartmentDto dto) throws NotFoundException;
    DepartmentDto create(@Valid DepartmentDto dto);
    void delete(@Valid DepartmentDto dto);
}
