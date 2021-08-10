package practice.language;

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
@RequestMapping("language")
public class LanguageController {

    @Autowired
    LanguageService service;

    @GetMapping(value = "{id}")
    public ResponseEntity<LanguageDto> getById(@PathVariable @Min(1) int id) {
        LanguageDto result = service.find(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<LanguageDto>> getAll() {
        List<LanguageDto> result = service.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<LanguageDto> create(@Valid @RequestBody LanguageDto dto) {
        LanguageDto result = service.create(dto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<LanguageDto> update(@Valid @RequestBody LanguageDto dto) {
        service.update(dto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<LanguageDto> delete(@Valid @RequestBody LanguageDto dto) {
        service.delete(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
