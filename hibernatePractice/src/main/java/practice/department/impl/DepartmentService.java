package practice.department.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import practice.department.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService implements practice.department.DepartmentService {

    @Autowired
    @Qualifier("departmentDaoJpa")
    private DepartmentDao dao;

    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private DepartmentConverter converter;


    @Override
    public DepartmentDto find(int id) {
        return converter.toDepartmentDto(dao.find(id));
    }

    @Override
    public List<DepartmentDto> findAll() {
        return repository.findAll().stream()
                .map(x -> converter.toDepartmentDto(x))
                .collect(Collectors.toList());
    }

    @Override
    public void update(DepartmentDto dto) {
        dao.update(converter.toDepartment(dto));
    }

    @Override
    public DepartmentDto create(DepartmentDto dto) {
        Department department = dao.create(converter.toDepartment(dto));
        return converter.toDepartmentDto(department);
    }

    @Override
    public void delete(DepartmentDto dto) {
        dao.delete(converter.toDepartment(dto));
    }
}
