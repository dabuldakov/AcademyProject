package practice.objects.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import practice.objects.document.service.DocumentService;
import practice.validation.validator.Marker;

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
        DocumentDto result = service.find(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping()
    ResponseEntity<List<DocumentDto>> getAll() {
        List<DocumentDto> result = service.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    @Validated({Marker.OnCreate.class})
    ResponseEntity<DocumentDto> create(@Valid @RequestBody DocumentDto dto) {
        DocumentDto result = service.create(dto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping
    @Validated({Marker.OnUpdate.class})
    ResponseEntity<DocumentDto> update(@Valid @RequestBody DocumentDto dto) {
        service.update(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping()
    @Validated({Marker.OnCreate.class})
    ResponseEntity<DocumentDto> delete(@Valid @RequestBody DocumentDto dto) {
        service.delete(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
