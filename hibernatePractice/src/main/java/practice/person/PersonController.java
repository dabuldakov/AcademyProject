package practice.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import practice.exception.AccessException;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    PersonService service;

    @Autowired
    Environment environment;

    @GetMapping(value = "{id}")
    ResponseEntity<PersonDto> getById(@RequestHeader(value = "access_key") String accessKey,
                                      @PathVariable @Min(1) int id) {
        if (accessKey.equals(environment.getProperty("rest.accessKey"))) {
                PersonDto result = service.find(id);
                return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            throw new AccessException();
        }
    }

    @GetMapping()
    ResponseEntity<List<PersonDto>> getAll(@RequestHeader(value = "access_key") String accessKey) {
        if (accessKey.equals(environment.getProperty("rest.accessKey"))) {
                List<PersonDto> result = service.findAll();
                return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            throw new AccessException();
        }
    }

    @PostMapping
    ResponseEntity<PersonDto> create(@RequestHeader(value = "access_key") String accessKey,
                                     @Valid @RequestBody PersonDto dto) {
        if (accessKey.equals(environment.getProperty("rest.accessKey"))) {
            PersonDto result = service.create(dto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            throw new AccessException();
        }

    }

    @PostMapping("list")
    ResponseEntity<List<PersonDto>> createList(@RequestHeader(value = "access_key") String accessKey,
                                               @RequestBody ArrayList<PersonDto> list) {
        if (accessKey.equals(environment.getProperty("rest.accessKey"))) {
            List<PersonDto> serviceList = service.createList(list);
            return new ResponseEntity<>(serviceList, HttpStatus.CREATED);
        } else {
            throw new AccessException();
        }
    }

    @PutMapping
    ResponseEntity<PersonDto> update(@RequestHeader(value = "access_key") String accessKey,
                                     @Valid @RequestBody PersonDto dto) {
        if (accessKey.equals(environment.getProperty("rest.accessKey"))) {
            service.update(dto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            throw new AccessException();
        }
    }

    @DeleteMapping
    ResponseEntity<PersonDto> delete(@RequestHeader(value = "access_key") String accessKey,
                                     @Valid @RequestBody PersonDto dto) {
        if (accessKey.equals(environment.getProperty("rest.accessKey"))) {
            service.delete(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new AccessException();
        }
    }
}
