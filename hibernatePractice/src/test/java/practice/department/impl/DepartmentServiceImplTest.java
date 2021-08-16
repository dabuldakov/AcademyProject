package practice.department.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import practice.Utils;
import practice.department.model.Department;
import practice.department.dao.DepartmentDao;
import practice.department.model.DepartmentDto;
import practice.department.service.DepartmentServiceImpl;
import practice.mapper.Mapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @InjectMocks
    DepartmentServiceImpl service;

    @Mock
    DepartmentDao dao;

    @Mock
    Mapper mapper;

    Department departmentCity;
    DepartmentDto departmentDtoCity;
    List<Department> departmentList;
    List<DepartmentDto> departmentDtoList;

    @Test
    void findShouldReturnDepartmentDto() {
        //given
        departmentCity = Utils.createDepartmentCity();
        departmentDtoCity = Utils.createDepartmentDtoCity();
        //when
        Mockito.when(dao.find(1)).thenReturn(departmentCity);
        Mockito.when(mapper.convert(departmentCity, DepartmentDto.class)).thenReturn(departmentDtoCity);
        DepartmentDto departmentDtoResult = service.find(1);
        //then
        Mockito.verify(dao).find(1);
        Mockito.verify(mapper).convert(departmentCity, DepartmentDto.class);
        assertEquals(departmentCity.getName(), departmentDtoResult.getName());
    }

    @Test
    void findAll() {
        //given
        departmentList = Utils.createDepartmentList();
        departmentDtoList = Utils.createDepartmentDtoList();
        //when
        Mockito.when(dao.findAll()).thenReturn(departmentList);
        Mockito.when(mapper.convert(departmentList.get(0), DepartmentDto.class)).thenReturn(departmentDtoList.get(0));
        Mockito.when(mapper.convert(departmentList.get(1), DepartmentDto.class)).thenReturn(departmentDtoList.get(1));
        Mockito.when(mapper.convert(departmentList.get(2), DepartmentDto.class)).thenReturn(departmentDtoList.get(2));
        List<DepartmentDto> result = service.findAll();
        //then
        Mockito.verify(dao).findAll();
        Mockito.verify(mapper).convert(departmentList.get(0), DepartmentDto.class);
        Mockito.verify(mapper).convert(departmentList.get(1), DepartmentDto.class);
        Mockito.verify(mapper).convert(departmentList.get(2), DepartmentDto.class);
        assertEquals(departmentDtoList.size(), result.size());
        assertEquals(departmentDtoList.get(0), result.get(0));
        assertEquals(departmentDtoList.get(1), result.get(1));
        assertEquals(departmentDtoList.get(2), result.get(2));
    }

    @Test
    void updateShouldNotReturnException() {
        //given
        departmentCity = Utils.createDepartmentCity();
        departmentDtoCity = Utils.createDepartmentDtoCity();
        //when
        Mockito.when(mapper.convert(departmentDtoCity, Department.class)).thenReturn(departmentCity);
        service.update(departmentDtoCity);
        //then
        Mockito.verify(mapper).convert(departmentDtoCity, Department.class);
        Mockito.verify(dao).update(departmentCity);
    }

    @Test
    void createShouldReturnDepartmentWithId() {
        //given
        departmentDtoCity = Utils.createDepartmentDtoCity();
        departmentCity = Utils.createDepartmentCity();
        //when
        Mockito.when(mapper.convert(departmentDtoCity, Department.class)).thenReturn(departmentCity);
        Mockito.when(dao.create(departmentCity)).thenReturn(departmentCity);
        Mockito.when(mapper.convert(departmentCity, DepartmentDto.class)).thenReturn(departmentDtoCity);
        DepartmentDto result = service.create(departmentDtoCity);
        //then
        Mockito.verify(mapper).convert(departmentDtoCity, Department.class);
        Mockito.verify(mapper).convert(departmentCity, DepartmentDto.class);
        Mockito.verify(dao).create(departmentCity);
        assertEquals(departmentDtoCity.getName(), result.getName());
    }

    @Test
    void deleteShouldVerifyDaoDelete() {
        //given
        departmentDtoCity = Utils.createDepartmentDtoCity();
        departmentCity = Utils.createDepartmentCity();
        //when
        Mockito.when(mapper.convert(departmentDtoCity, Department.class)).thenReturn(departmentCity);
        service.delete(departmentDtoCity);
        //then
        Mockito.verify(dao).delete(departmentCity);
        Mockito.verify(mapper).convert(departmentDtoCity, Department.class);
    }
}