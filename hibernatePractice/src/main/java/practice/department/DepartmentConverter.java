package practice.department;

import org.springframework.stereotype.Component;

@Component
public class DepartmentConverter {
    public Department toDepartment(DepartmentDto dto){
        Department department = new Department();
        department.setId(dto.getId());
        department.setName(dto.getName());
        return department;
    }

    public DepartmentDto toDepartmentDto(Department department){
        DepartmentDto dto = new DepartmentDto();
        dto.setId(department.getId());
        dto.setName(department.getName());
        return dto;
    }
}
