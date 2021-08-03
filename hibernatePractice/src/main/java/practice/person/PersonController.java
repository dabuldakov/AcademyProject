package practice.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.person.exception.PersonAccessException;
import practice.person.exception.PersonException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    PersonService service;

    @Autowired
    Environment environment;

    @GetMapping(value = "{id}")
    ResponseEntity<PersonDto> getById(@RequestHeader(value = "access_key") String accessKey, @PathVariable int id) throws PersonException {
        if (accessKey.equals(environment.getProperty("rest.accessKey"))) {
                PersonDto result = service.find(id);
                return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            throw new PersonAccessException();
        }
    }

    @GetMapping()
    ResponseEntity<List<PersonDto>> getAll(@RequestHeader(value = "access_key") String accessKey) throws PersonException {
        if (accessKey.equals(environment.getProperty("rest.accessKey"))) {
                List<PersonDto> result = service.findAll();
                return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            throw new PersonAccessException();
        }
    }

    @PostMapping()
    ResponseEntity<PersonDto> create(@RequestHeader(value = "access_key") String accessKey, @RequestBody PersonDto dto) throws PersonException {
        if (accessKey.equals(environment.getProperty("rest.accessKey"))) {
            PersonDto result = service.create(dto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            throw new PersonAccessException();
        }

    }

    @PostMapping("list")
    ResponseEntity<List<PersonDto>> createList(@RequestHeader(value = "access_key") String accessKey, @RequestBody ArrayList<PersonDto> list) throws PersonException {
        if (accessKey.equals(environment.getProperty("rest.accessKey"))) {
            List<PersonDto> serviceList = service.createList(list);
            return new ResponseEntity<>(serviceList, HttpStatus.CREATED);
        } else {
            throw new PersonAccessException();
        }
    }

    @PutMapping
    ResponseEntity<PersonDto> update(@RequestHeader(value = "access_key") String accessKey, @RequestBody PersonDto dto) throws PersonException {
        if (accessKey.equals(environment.getProperty("rest.accessKey"))) {
            service.update(dto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            throw new PersonAccessException();
        }
    }

    @DeleteMapping
    ResponseEntity<PersonDto> delete(@RequestHeader(value = "access_key") String accessKey, @RequestBody PersonDto dto) throws PersonException {
        if (accessKey.equals(environment.getProperty("rest.accessKey"))) {
            service.delete(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new PersonAccessException();
        }
    }
}
