package practice.person.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import practice.department.Department;
import practice.department.DepartmentDto;
import practice.document.Document;
import practice.document.DocumentDto;
import practice.exception.IdException;
import practice.language.Language;
import practice.language.LanguageDto;
import practice.mapper.Mapper;
import practice.person.Person;
import practice.person.PersonDao;
import practice.person.PersonDto;
import practice.util.Constants;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @InjectMocks
    PersonServiceImpl service;
    @Mock
    PersonDao personDao;
    @Mock
    Mapper mapper;

    @Test
    void findShouldReturnPersonDto() {
        //given
        Person person = createPerson("Sergey");
        PersonDto personDto = createPersonDto("Sergey");
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
        List<Person> personList = createPersonList();
        List<PersonDto> personDtoList = createPersonDtoList();
        //when
        Mockito.when(personDao.findAll()).thenReturn(personList);
        Mockito.when(mapper.convert(personList.get(0), PersonDto.class)).thenReturn(personDtoList.get(0));
        Mockito.when(mapper.convert(personList.get(1), PersonDto.class)).thenReturn(personDtoList.get(1));
        Mockito.when(mapper.convert(personList.get(2), PersonDto.class)).thenReturn(personDtoList.get(2));
        List<PersonDto> personDtoResult = service.findAll();
        //then
        Mockito.verify(personDao).findAll();
        assertEquals(3, personDtoResult.size());
        for (PersonDto personDto : personDtoResult) {
            assertEquals(personDto.getFirstName(), personDto.getFirstName());
        }
        // TODO: 8/12/2021 assertJ compare lists
    }

    @Test
    void updateShouldReturnThrow() {
        //given
        PersonDto personDto = new PersonDto();
        //when
        IdException idException = assertThrows(IdException.class, () -> {
            service.update(personDto);
        });
        //then
        assertEquals("Id incorrect: Person id is empty.", idException.getMessage());
    }

    @Test
    void updateShouldNotGetException() {
        //given
        PersonDto personDto = new PersonDto();
        personDto.setId(1);
        //when
        service.update(personDto);
        //then
        Mockito.verify(personDao).update(any());
    }


    @Test
    void createShouldReturnPersonDtoWithId() {
        //given
        PersonDto personDto = new PersonDto();
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
        ArrayList<PersonDto> personDtoList = createPersonDtoList();
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
    void deleteShouldReturnThrow() {
        //given
        PersonDto personDto = new PersonDto();
        //when
        IdException idException = assertThrows(IdException.class, () -> {
            service.delete(personDto);
        });
        //then
        assertEquals("Id incorrect: Person id: " + 0, idException.getMessage());
    }

    @Test
    void deleteShouldNotGetException() {
        //given
        PersonDto personDto = new PersonDto();
        personDto.setId(1);
        //when
        service.delete(personDto);
        //then
        Mockito.verify(personDao).delete(any());
    }

    @Test
    void getAllByFirstName() {
    }

    private List<Person> createPersonList() {
        List<Person> personList = new ArrayList<>();
        personList.add(createPerson("Sergey"));
        personList.add(createPerson("Vladimir"));
        personList.add(createPerson("Vitaly"));
        return personList;
    }

    private ArrayList<PersonDto> createPersonDtoList() {
        ArrayList<PersonDto> personDtoList = new ArrayList<>();
        personDtoList.add(createPersonDto("Sergey"));
        personDtoList.add(createPersonDto("Vladimir"));
        personDtoList.add(createPersonDto("Vitaly"));
        return personDtoList;
    }

    private Person createPerson(String name) {
        Person person = new Person();
        person.setId(1);
        person.setFirstName(name);
        person.setSecondName("test second name");
        try {
            person.setBirthday(Constants.DATE_FORMAT.parse("2021-08-12"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        person.setLanguage(createLanguageList());
        person.setDepartment(createDepartmentCity());
        person.setDocument(createDocument("zzz-sss-qqq"));
        return person;
    }

    private PersonDto createPersonDto(String name) {
        PersonDto personDto = new PersonDto();
        personDto.setId(1);
        personDto.setFirstName(name);
        personDto.setSecondName("test second name");
        try {
            personDto.setBirthday(Constants.DATE_FORMAT.parse("2021-08-12"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        personDto.setLanguage(createLanguageDtoList());
        personDto.setDepartment(createDepartmentDtoCity());
        personDto.setDocument(createDocumentDto("zzz-sss-qqq"));
        return personDto;
    }

    private Department createDepartmentCity() {
        Department department = new Department();
        department.setId(1);
        department.setName("City");
        return department;
    }

    private List<Language> createLanguageList() {
        Language language1 = new Language();
        language1.setId(1);
        language1.setName("Russian");
        Language language2 = new Language();
        language2.setId(2);
        language2.setName("English");
        List<Language> languageList = new ArrayList<>();
        languageList.add(language1);
        languageList.add(language2);
        return languageList;
    }

    private Document createDocument(String number) {
        Date date = new Date();
        Document document = new Document();
        document.setId(1);
        document.setExpiryDate(date);
        document.setNumber(number);
        return document;
    }

    private List<LanguageDto> createLanguageDtoList() {
        LanguageDto languageDto1 = new LanguageDto();
        languageDto1.setId(1);
        languageDto1.setName("Russian");
        LanguageDto languageDto2 = new LanguageDto();
        languageDto2.setId(2);
        languageDto2.setName("English");
        List<LanguageDto> languageDtoList = new ArrayList<>();
        languageDtoList.add(languageDto2);
        languageDtoList.add(languageDto2);
        return languageDtoList;
    }

    private DocumentDto createDocumentDto(String number) {
        Date date = new Date();
        DocumentDto documentDto = new DocumentDto();
        documentDto.setId(1);
        documentDto.setExpiryDate(date);
        documentDto.setNumber(number);
        return documentDto;
    }

    private DepartmentDto createDepartmentDtoCity() {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(1);
        departmentDto.setName("City");
        return departmentDto;
    }
}