package practice.department.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import practice.department.*;
import practice.mapper.Mapper;

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
    Mapper mapper;


    @Override
    public DepartmentDto find(int id) {
        return mapper.run(dao.find(id), DepartmentDto.class);
    }

    @Override
    public List<DepartmentDto> findAll() {
        return repository.findAll().stream()
                .map(x -> (mapper.run(x, DepartmentDto.class)))
                .collect(Collectors.toList());
    }

    @Override
    public void update(DepartmentDto dto) {
        dao.update(mapper.run(dto, Department.class));
    }

    @Override
    public DepartmentDto create(DepartmentDto dto) {
        Department department = dao.create(mapper.run(dto, Department.class));
        return mapper.run(department, DepartmentDto.class);
    }

    @Override
    public void delete(DepartmentDto dto) {
        dao.delete(mapper.run(dto, Department.class));
    }
}
