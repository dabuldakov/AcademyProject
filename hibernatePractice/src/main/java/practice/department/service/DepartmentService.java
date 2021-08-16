package practice.department.service;

import practice.department.model.DepartmentDto;
import practice.exception.NotFoundException;

import java.util.List;

public interface DepartmentService {
    DepartmentDto find(int id);
    List<DepartmentDto> findAll();
    void update(DepartmentDto dto) throws NotFoundException;
    DepartmentDto create(DepartmentDto dto);
    void delete(DepartmentDto dto);
}
