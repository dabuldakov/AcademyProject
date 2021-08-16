package practice;

import practice.department.model.Department;
import practice.department.model.DepartmentDto;
import practice.document.model.Document;
import practice.document.model.DocumentDto;
import practice.language.model.Language;
import practice.language.model.LanguageDto;
import practice.person.model.Person;
import practice.person.model.PersonDto;
import practice.util.Constants;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Utils {
    public static List<Person> createPersonList() {
        List<Person> personList = new ArrayList<>();
        personList.add(createPerson("Sergey"));
        personList.add(createPerson("Vladimir"));
        personList.add(createPerson("Vitaly"));
        return personList;
    }

    public static ArrayList<PersonDto> createPersonDtoList() {
        ArrayList<PersonDto> personDtoList = new ArrayList<>();
        personDtoList.add(createPersonDto("Sergey"));
        personDtoList.add(createPersonDto("Vladimir"));
        personDtoList.add(createPersonDto("Vitaly"));
        return personDtoList;
    }

    public static Person createPerson(String name) {
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
        person.setDocument(createDocumentOne());
        return person;
    }

    public static PersonDto createPersonDto(String name) {
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
        personDto.setDocument(createDocumentDtoOne());
        return personDto;
    }

    public static List<Language> createLanguageList() {
        List<Language> languageList = new ArrayList<>();
        languageList.add(createLanguageRussia());
        languageList.add(createLanguageEnglish());
        return languageList;
    }

    public static Language createLanguageRussia(){
        Language language = new Language();
        language.setId(1);
        language.setName("Russia");
        return language;
    }

    public static Language createLanguageEnglish(){
        Language language = new Language();
        language.setId(2);
        language.setName("English");
        return language;
    }

    public static List<LanguageDto> createLanguageDtoList() {

        List<LanguageDto> languageDtoList = new ArrayList<>();
        languageDtoList.add(createLanguageDtoRussia());
        languageDtoList.add(createLanguageDtoEnglish());
        return languageDtoList;
    }

    public static LanguageDto createLanguageDtoRussia(){
        LanguageDto languageDto = new LanguageDto();
        languageDto.setId(1);
        languageDto.setName("Russia");
        return languageDto;
    }

    public static LanguageDto createLanguageDtoEnglish(){
        LanguageDto languageDto = new LanguageDto();
        languageDto.setId(2);
        languageDto.setName("English");
        return languageDto;
    }

    public static Document createDocumentOne() {
        Date date = new Date();
        Document document = new Document();
        document.setId(1);
        document.setExpiryDate(date);
        document.setNumber("xxx");
        return document;
    }

    public static Document createDocumentTwo() {
        Date date = new Date();
        Document document = new Document();
        document.setId(2);
        document.setExpiryDate(date);
        document.setNumber("yyy");
        return document;
    }

    public static Document createDocumentThree() {
        Date date = new Date();
        Document document = new Document();
        document.setId(3);
        document.setExpiryDate(date);
        document.setNumber("zzz");
        return document;
    }

    public static List<Document> createDocumentList(){
        List<Document> list = new ArrayList<>();
        list.add(createDocumentOne());
        list.add(createDocumentTwo());
        list.add(createDocumentThree());
        return list;
    }

    public static DocumentDto createDocumentDtoOne() {
        Date date = new Date();
        DocumentDto documentDto = new DocumentDto();
        documentDto.setId(1);
        documentDto.setExpiryDate(date);
        documentDto.setNumber("xxx");
        return documentDto;
    }

    public static DocumentDto createDocumentDtoTwo() {
        Date date = new Date();
        DocumentDto documentDto = new DocumentDto();
        documentDto.setId(2);
        documentDto.setExpiryDate(date);
        documentDto.setNumber("yyy");
        return documentDto;
    }

    public static DocumentDto createDocumentDtoThree() {
        Date date = new Date();
        DocumentDto documentDto = new DocumentDto();
        documentDto.setId(3);
        documentDto.setExpiryDate(date);
        documentDto.setNumber("zzz");
        return documentDto;
    }

    public static List<DocumentDto> createDocumentDtoList(){
        List<DocumentDto> list = new ArrayList<>();
        list.add(createDocumentDtoOne());
        list.add(createDocumentDtoTwo());
        list.add(createDocumentDtoThree());
        return list;
    }

    public static Department createDepartmentCity() {
        Department department = new Department();
        department.setId(1);
        department.setName("City");
        return department;
    }

    public static Department createDepartmentVillage() {
        Department department = new Department();
        department.setId(1);
        department.setName("Village");
        return department;
    }

    public static Department createDepartmentCapital() {
        Department department = new Department();
        department.setId(1);
        department.setName("Capital");
        return department;
    }

    public static List<Department> createDepartmentList(){
        List<Department> list = new ArrayList<>();
        list.add(createDepartmentCity());
        list.add(createDepartmentVillage());
        list.add(createDepartmentCapital());
        return list;
    }

    public static DepartmentDto createDepartmentDtoCity() {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(1);
        departmentDto.setName("City");
        return departmentDto;
    }

    public static DepartmentDto createDepartmentDtoVillage() {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(1);
        departmentDto.setName("Village");
        return departmentDto;
    }

    public static DepartmentDto createDepartmentDtoCapital() {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(1);
        departmentDto.setName("Capital");
        return departmentDto;
    }

    public static List<DepartmentDto> createDepartmentDtoList(){
        List<DepartmentDto> list = new ArrayList<>();
        list.add(createDepartmentDtoVillage());
        list.add(createDepartmentDtoCity());
        list.add(createDepartmentDtoCapital());
        return list;
    }
}
