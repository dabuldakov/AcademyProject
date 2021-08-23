package practice.objects.language;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import practice.objects.language.service.LanguageService;
import practice.validation.validator.Marker;

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
    @Validated({Marker.OnCreate.class})
    public ResponseEntity<LanguageDto> create(@Valid @RequestBody LanguageDto dto) {
        LanguageDto result = service.create(dto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping
    @Validated({Marker.OnUpdate.class})
    public ResponseEntity<LanguageDto> update(@Valid @RequestBody LanguageDto dto) {
        service.update(dto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping
    @Validated({Marker.OnCreate.class})
    public ResponseEntity<LanguageDto> delete(@Valid @RequestBody LanguageDto dto) {
        service.delete(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
