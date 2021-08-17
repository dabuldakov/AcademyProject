package practice.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import practice.person.model.PersonDto;
import practice.person.service.PersonService;
import practice.valid.validator.Marker;

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
    ResponseEntity<PersonDto> getById(@PathVariable @Min(1) int id) {
                return new ResponseEntity<>(service.find(id), HttpStatus.OK);
    }

    @GetMapping()
    ResponseEntity<List<PersonDto>> getAll() {
                return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @Validated({Marker.OnCreate.class})
    ResponseEntity<PersonDto> create(@Valid @RequestBody PersonDto dto) {
            return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @PostMapping("list")
    ResponseEntity<List<PersonDto>> createList(@RequestBody ArrayList<@Valid PersonDto> list) {
            return new ResponseEntity<>(service.createList(list), HttpStatus.CREATED);
    }

    @PutMapping
    @Validated({Marker.OnUpdate.class})
    ResponseEntity<PersonDto> update(@Valid @RequestBody PersonDto dto) {
            service.update(dto);
            return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping
    @Validated({Marker.OnCreate.class})
    ResponseEntity<PersonDto> delete(@Valid @RequestBody PersonDto dto) {
            service.delete(dto);
            return new ResponseEntity<>(HttpStatus.OK);
    }
}
