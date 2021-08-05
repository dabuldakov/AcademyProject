package practice.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("document")
public class DocumentController {

    @Autowired
    DocumentService service;

    @GetMapping("{id}")
    ResponseEntity<DocumentDto> getById(@PathVariable @Min(1) int id) {
        try {
            DocumentDto result = service.find(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    ResponseEntity<List<DocumentDto>> getAll(){
        try {
            List<DocumentDto> result = service.findAll();
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    ResponseEntity<DocumentDto> create(@Valid @RequestBody DocumentDto dto){
        try{
            DocumentDto result = service.create(dto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    ResponseEntity<DocumentDto> update(@Valid @RequestBody DocumentDto dto){
        try{
            service.update(dto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping()
    ResponseEntity<DocumentDto> delete(@Valid @RequestBody DocumentDto dto){
        try {
            service.delete(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
