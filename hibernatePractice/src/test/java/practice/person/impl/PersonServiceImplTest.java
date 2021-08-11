package practice.person.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import practice.department.Department;
import practice.department.DepartmentDto;
import practice.document.Document;
import practice.document.DocumentDto;
import practice.language.Language;
import practice.language.LanguageDto;
import practice.mapper.Mapper;
import practice.person.Person;
import practice.person.PersonDao;
import practice.person.PersonDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @InjectMocks
    PersonServiceImpl service;
    @Mock
    PersonDao personDao;
    @Spy
    Mapper mapper;

    @Test
    void findShouldReturnPersonDto() {
        //given
        Person person = createPerson("Sergey");

        //when
        Mockito.when(personDao.find(1)).thenReturn(person);
        PersonDto personDtoResult = service.find(1);

        //then
        Mockito.verify(personDao).find(1);
        assertEquals(person.getFirstName(), personDtoResult.getFirstName());
        assertEquals(person.getSecondName(), personDtoResult.getSecondName());
        assertEquals(person.getBirthday(), personDtoResult.getBirthday());
        assertEquals(person.getDepartment().getName(), personDtoResult.getDepartment().getName());
        assertEquals(person.getLanguage().size(), 2);
        assertEquals(person.getDocument().getNumber(), personDtoResult.getDocument().getNumber());
    }

    @Test
    void findAllShouldReturnPersonList() {
        //given
        List<Person> personList = createPersonList();
        //when
        Mockito.when(personDao.findAll()).thenReturn(personList);
        List<PersonDto> personDtoResult = service.findAll();

        //then
        Mockito.verify(personDao).findAll();
        assertEquals(3, personDtoResult.size());
        for (PersonDto personDto : personDtoResult) {
            assertEquals(personDto.getFirstName(), personDto.getFirstName());
        }
    }

    @Test
    void update() {
    }

    @Test
    void create() {
        Date date = new Date();
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(1);
        departmentDto.setName("City");
        DocumentDto documentDto = new DocumentDto();
        documentDto.setId(1);
        documentDto.setExpiryDate(date);
        documentDto.setNumber("qqq-aaa-zzz");
        LanguageDto languageDto1 = new LanguageDto();
        languageDto1.setId(1);
        languageDto1.setName("Russian");
        LanguageDto languageDto2 = new LanguageDto();
        languageDto2.setId(2);
        languageDto2.setName("English");
        List<LanguageDto> languageDtoList = new ArrayList<>();
        languageDtoList.add(languageDto2);
        languageDtoList.add(languageDto2);
        PersonDto personDto = new PersonDto();
        personDto.setId(1);
        personDto.setFirstName("test name");
        personDto.setSecondName("test second name");
        personDto.setBirthday(date);
        personDto.setLanguage(languageDtoList);
        personDto.setDepartment(departmentDto);
        personDto.setDocument(documentDto);
    }

    @Test
    void delete() {
    }

    @Test
    void createList() {
    }

    @Test
    void getAllByFirstName() {
    }

    private List<Person> createPersonList(){
        List<Person> personList = new ArrayList<>();
        personList.add(createPerson("Sergey"));
        personList.add(createPerson("Vladimir"));
        personList.add(createPerson("Vitaly"));
        return personList;
    }

    private Person createPerson(String name){
        Date date = new Date();
        Person person = new Person();
        person.setId(1);
        person.setFirstName(name);
        person.setSecondName("test second name");
        person.setBirthday(date);
        person.setLanguage(createLanguageList());
        person.setDepartment(createDepartmentCity());
        person.setDocument(createDocument("zzz-sss-qqq"));
        return person;
    }

    private Department createDepartmentCity(){
        Department department = new Department();
        department.setId(1);
        department.setName("City");
        return department;
    }

    private List<Language> createLanguageList(){
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

    private Document createDocument(String number){
        Date date = new Date();
        Document document = new Document();
        document.setId(1);
        document.setExpiryDate(date);
        document.setNumber(number);
        return document;
    }
}