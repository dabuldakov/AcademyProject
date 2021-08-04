package practice.language;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import practice.person.PersonDto;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("language")
public class LanguageController{

    @Autowired
    LanguageService service;

    @GetMapping(value = "{id}")
    public ResponseEntity<LanguageDto> getById(@PathVariable @Min(1) int id) {
        try{
            LanguageDto result = service.find(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<List<LanguageDto>> getAll() {
        try {
            List<LanguageDto> result = service.findAll();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public ResponseEntity<LanguageDto> create(@Valid @RequestBody LanguageDto dto) {
        try{
            LanguageDto result = service.create(dto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    public ResponseEntity<LanguageDto> update(@Valid @RequestBody LanguageDto dto) {
        try{
            service.update(dto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<LanguageDto> delete(@Valid @RequestBody LanguageDto dto) {
        try{
            service.delete(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
