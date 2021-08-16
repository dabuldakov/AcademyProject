package practice.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import practice.department.model.DepartmentDto;
import practice.department.service.DepartmentService;
import practice.valid.validator.Marker;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    DepartmentService service;

    @GetMapping("{id}")
    ResponseEntity<DepartmentDto> getById(@PathVariable @Min(1) int id) {
        DepartmentDto dto = service.find(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping()
    ResponseEntity<List<DepartmentDto>> getAll() {
        List<DepartmentDto> all = service.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @PostMapping
    @Validated({Marker.OnCreate.class})
    ResponseEntity<DepartmentDto> create(@Valid @RequestBody DepartmentDto dto) {
        DepartmentDto result = service.create(dto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping
    @Validated({Marker.OnUpdate.class})
    ResponseEntity<DepartmentDto> update(@Valid @RequestBody DepartmentDto dto) {
        service.update(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping()
    @Validated({Marker.OnCreate.class})
    ResponseEntity<DepartmentDto> delete(@Valid @RequestBody DepartmentDto dto) {
        service.delete(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
