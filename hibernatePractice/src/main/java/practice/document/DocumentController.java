package practice.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("document")
public class DocumentController {

    @Autowired
    DocumentService service;

    @GetMapping("{id}")
    ResponseEntity<DocumentDto> getById(@PathVariable int id) {
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

    ResponseEntity<DocumentDto> create(@RequestBody DocumentDto dto){
        return null;
    }
}
