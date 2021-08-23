package practice.objects.department.service;

import practice.objects.department.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto find(int id);
    List<DepartmentDto> findAll();
    void update(DepartmentDto dto);
    DepartmentDto create(DepartmentDto dto);
    void delete(DepartmentDto dto);
}
