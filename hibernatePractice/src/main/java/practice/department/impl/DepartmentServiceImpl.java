package practice.department.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import practice.exception.NotFoundException;
import practice.department.*;
import practice.mapper.Mapper;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    @Qualifier("departmentDaoJpa")
    private DepartmentDao dao;

    @Autowired
    Mapper mapper;

    @Override
    public DepartmentDto find(int id) {
        DepartmentDto departmentDto;
        departmentDto = mapper.convert(dao.find(id), DepartmentDto.class);
        if (departmentDto == null){
            throw new NotFoundException();
        }
        return departmentDto;
    }

    @Override
    public List<DepartmentDto> findAll() {
        return dao.findAll().stream()
                .map(x -> (mapper.convert(x, DepartmentDto.class)))
                .collect(Collectors.toList());
    }

    @Override
    public void update(@Valid DepartmentDto dto) throws NotFoundException {
        dao.update(mapper.convert(dto, Department.class));
    }

    @Override
    public DepartmentDto create(@Valid DepartmentDto dto) {
        Department department = dao.create(mapper.convert(dto, Department.class));
        return mapper.convert(department, DepartmentDto.class);
    }

    @Override
    public void delete(@Valid DepartmentDto dto) {
        dao.delete(mapper.convert(dto, Department.class));
    }
}
