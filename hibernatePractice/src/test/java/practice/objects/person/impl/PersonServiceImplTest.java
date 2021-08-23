package practice.objects.person.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import practice.Utils;
import practice.mapper.Mapper;
import practice.objects.person.Person;
import practice.objects.person.PersonDto;
import practice.objects.person.dao.PersonDao;
import practice.objects.person.service.PersonServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static practice.Utils.createPerson;
import static practice.Utils.createPersonDto;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @InjectMocks
    PersonServiceImpl service;
    @Mock
    PersonDao personDao;
    @Mock
    Mapper mapper;

    Person person;
    PersonDto personDto;
    List<Person> personList;
    List<PersonDto> personDtoList;

    @Test
    void findShouldReturnPersonDto() {
        //given
        person = createPerson("Sergey");
        personDto = Utils.createPersonDto("Sergey");
        //when
        Mockito.when(personDao.find(1)).thenReturn(person);
        Mockito.when(mapper.convert(person, PersonDto.class)).thenReturn(personDto);
        PersonDto personDtoResult = service.find(1);
        //then
        Mockito.verify(personDao).find(1);
        Mockito.verify(mapper).convert(person, PersonDto.class);
        assertEquals(person.getFirstName(), personDtoResult.getFirstName());
        assertEquals(person.getSecondName(), personDtoResult.getSecondName());
        assertEquals(person.getBirthday(), personDtoResult.getBirthday());
        assertEquals(person.getDepartment().getName(), personDtoResult.getDepartment().getName());
        assertEquals(person.getLanguage().size(), personDtoResult.getLanguage().size());
        assertEquals(person.getDocument().getNumber(), personDtoResult.getDocument().getNumber());
    }

    @Test
    void findAllShouldReturnPersonDtoList() {
        //given
        personList = Utils.createPersonList();
        personDtoList = Utils.createPersonDtoList();
        //when
        Mockito.when(personDao.findAll()).thenReturn(personList);
        Mockito.when(mapper.convert(personList.get(0), PersonDto.class)).thenReturn(personDtoList.get(0));
        Mockito.when(mapper.convert(personList.get(1), PersonDto.class)).thenReturn(personDtoList.get(1));
        Mockito.when(mapper.convert(personList.get(2), PersonDto.class)).thenReturn(personDtoList.get(2));
        List<PersonDto> personDtoResult = service.findAll();
        //then
        Mockito.verify(personDao).findAll();
        Mockito.verify(mapper).convert(personList.get(0), PersonDto.class);
        Mockito.verify(mapper).convert(personList.get(1), PersonDto.class);
        Mockito.verify(mapper).convert(personList.get(2), PersonDto.class);
        assertEquals(3, personDtoResult.size());
        assertEquals(personDtoResult.get(0), personDtoList.get(0));
        assertEquals(personDtoResult.get(1), personDtoList.get(1));
        assertEquals(personDtoResult.get(2), personDtoList.get(2));
        // TODO: 8/12/2021 assertJ compare lists
    }

    @Test
    void updateShouldNotGetException() {
        //given
        personDto = Utils.createPersonDto("Sergey");
        person = createPerson("Sergey");
        //when
        Mockito.when(mapper.convert(personDto, Person.class)).thenReturn(person);
        service.update(personDto);
        //then
        Mockito.verify(personDao).update(person);
        Mockito.verify(mapper).convert(personDto, Person.class);
    }


    @Test
    void createShouldReturnPersonDtoWithId() {
        //given
        personDto = new PersonDto();
        personDto.setFirstName("Sergey");

        Person personFromMapper = new Person();
        personFromMapper.setFirstName("Sergey");

        Person personFromDao = new Person();
        personFromDao.setFirstName("Sergey");
        personFromDao.setId(1);

        Person personFromDaoFind = new Person();
        personFromDaoFind.setFirstName("Sergey");
        personFromDaoFind.setId(1);

        PersonDto personDtoFromFind = new PersonDto();
        personDtoFromFind.setFirstName("Sergey");
        personDtoFromFind.setId(1);
        //when
        Mockito.when(mapper.convert(personDto, Person.class)).thenReturn(personFromMapper);
        Mockito.when(personDao.create(personFromMapper)).thenReturn(personFromDao);
        Mockito.when(personDao.find(personFromDao.getId())).thenReturn(personFromDaoFind);
        Mockito.when(mapper.convert(personFromDaoFind, PersonDto.class)).thenReturn(personDtoFromFind);
        PersonDto personDtoResult = service.create(personDto);
        //then
        Mockito.verify(mapper).convert(personDto, Person.class);
        Mockito.verify(personDao).create(personFromMapper);
        Mockito.verify(personDao).find(personFromDao.getId());
        Mockito.verify(mapper).convert(personFromDaoFind, PersonDto.class);
        assertEquals(personDtoFromFind, personDtoResult);
    }

    @Test
    void createListShouldReturnPersonDtoList() {
        //given
        personDtoList = Utils.createPersonDtoList();
        PersonServiceImpl mock = Mockito.mock(PersonServiceImpl.class);
        Mockito.when(mock.createList(personDtoList)).thenCallRealMethod();
        Mockito.when(mock.create(personDtoList.get(0))).thenReturn(personDtoList.get(0));
        Mockito.when(mock.create(personDtoList.get(1))).thenReturn(personDtoList.get(1));
        Mockito.when(mock.create(personDtoList.get(2))).thenReturn(personDtoList.get(2));
        //when
        List<PersonDto> personDtoListResult = mock.createList(personDtoList);
        //then
        Mockito.verify(mock, Mockito.times(3)).create(any());
        assertEquals(3, personDtoListResult.size());
    }

    @Test
    void deleteShouldNotGetException() {
        //given
        personDto = new PersonDto();
        personDto.setId(1);
        person = new Person();
        person.setId(1);
        //when
        Mockito.when(mapper.convert(personDto, Person.class)).thenReturn(person);
        service.delete(personDto);
        //then
        Mockito.verify(personDao).delete(person);
    }

    @Test
    void getAllByFirstNameShouldReturnPersonDtoList() {
        //given
        personList = new ArrayList<>();
        personList.add(createPerson("Sergey"));
        personList.add(createPerson("Sergey"));
        personDtoList = new ArrayList<>();
        personDtoList.add(createPersonDto("Sergey"));
        personDtoList.add(createPersonDto("Sergey"));
        //when
        Mockito.when(mapper.convert(personList.get(0), PersonDto.class)).thenReturn(personDtoList.get(0));
        Mockito.when(mapper.convert(personList.get(1), PersonDto.class)).thenReturn(personDtoList.get(1));
        Mockito.when(personDao.getAllByFirstName("Sergey")).thenReturn(personList);
        List<PersonDto> personDtoListResult = service.getAllByFirstName("Sergey");
        //then
        Mockito.verify(mapper).convert(personList.get(0), PersonDto.class);
        Mockito.verify(mapper).convert(personList.get(1), PersonDto.class);
        Mockito.verify(personDao).getAllByFirstName("Sergey");
        assertEquals(personList.size(), personDtoListResult.size());
    }
}