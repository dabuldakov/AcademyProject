package practice.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.util.Constants;

import java.util.List;

@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    PersonService service;

    @GetMapping(value = "{id}")
    ResponseEntity<PersonDto> getById(@RequestHeader(value = "access_key") String accessKey, @PathVariable int id) {
        if (accessKey.equals(Constants.KEY)) {
            try {
                PersonDto result = service.find(id);
                return new ResponseEntity<>(result, HttpStatus.OK);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping()
    ResponseEntity<List<PersonDto>> getAll(@RequestHeader(value = "access_key") String accessKey) {
        if (accessKey.equals(Constants.KEY)) {
            try {
                List<PersonDto> result = service.findAll();
                return new ResponseEntity<>(result, HttpStatus.OK);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping()
    ResponseEntity<PersonDto> create(@RequestHeader(value = "access_key") String accessKey, @RequestBody PersonDto dto) {
        if (accessKey.equals(Constants.KEY)) {
            try {
                PersonDto result = service.create(dto);
                return new ResponseEntity<>(result, HttpStatus.CREATED);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

    @PutMapping
    ResponseEntity<PersonDto> update(@RequestHeader(value = "access_key") String accessKey, @RequestBody PersonDto dto) {
        if (accessKey.equals(Constants.KEY)) {
            try {
                service.update(dto);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping
    ResponseEntity<PersonDto> delete(@RequestHeader(value = "access_key") String accessKey, @RequestBody PersonDto dto) {
        if (accessKey.equals(Constants.KEY)) {
            try {
                service.delete(dto);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
