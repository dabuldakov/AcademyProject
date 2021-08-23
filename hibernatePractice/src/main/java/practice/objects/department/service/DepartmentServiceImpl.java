package practice.objects.department.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import practice.mapper.Mapper;
import practice.objects.department.Department;
import practice.objects.department.DepartmentDto;
import practice.objects.department.dao.DepartmentDao;

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
        return mapper.convert(dao.find(id), DepartmentDto.class);
    }

    @Override
    public List<DepartmentDto> findAll() {
        return dao.findAll().stream()
                .map(x -> (mapper.convert(x, DepartmentDto.class)))
                .collect(Collectors.toList());
    }

    @Override
    public void update(DepartmentDto dto){
        dao.update(mapper.convert(dto, Department.class));
    }

    @Override
    public DepartmentDto create(DepartmentDto dto) {
        Department department = dao.create(mapper.convert(dto, Department.class));
        return mapper.convert(department, DepartmentDto.class);
    }

    @Override
    public void delete(DepartmentDto dto) {
        dao.delete(mapper.convert(dto, Department.class));
    }
}
