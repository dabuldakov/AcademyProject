package practice.objects.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import practice.objects.person.service.PersonService;
import practice.validation.validator.Marker;

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

    @GetMapping(value = "{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    ResponseEntity<PersonDto> getById(@PathVariable @Min(1) int id) {
                return new ResponseEntity<>(service.find(id), HttpStatus.OK);
    }

    @GetMapping()
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    ResponseEntity<List<PersonDto>> getAll() {
                return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @Validated({Marker.OnCreate.class})
    @Secured({"ROLE_ADMIN"})
    ResponseEntity<PersonDto> create(@Valid @RequestBody PersonDto dto) {
            return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @PostMapping("list")
    @Secured({"ROLE_ADMIN"})
    ResponseEntity<List<PersonDto>> createList(@RequestBody ArrayList<@Valid PersonDto> list) {
            return new ResponseEntity<>(service.createList(list), HttpStatus.CREATED);
    }

    @PutMapping
    @Validated({Marker.OnUpdate.class})
    @Secured({"ROLE_ADMIN"})
    ResponseEntity<PersonDto> update(@Valid @RequestBody PersonDto dto) {
            service.update(dto);
            return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping
    @Validated({Marker.OnCreate.class})
    @Secured({"ROLE_ADMIN"})
    ResponseEntity<PersonDto> delete(@Valid @RequestBody PersonDto dto) {
            service.delete(dto);
            return new ResponseEntity<>(HttpStatus.OK);
    }
}
