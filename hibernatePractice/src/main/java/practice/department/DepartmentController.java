package practice.department;

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
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    DepartmentService service;

    @GetMapping("{id}")
    ResponseEntity<DepartmentDto> getById(@PathVariable @Min(1) int id){
            DepartmentDto dto = service.find(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping()
    ResponseEntity<List<DepartmentDto>> getAll(){
        try{
            List<DepartmentDto> all = service.findAll();
            return new ResponseEntity<>(all, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    ResponseEntity<DepartmentDto> create(@Valid @RequestBody DepartmentDto dto){
        try{
            DepartmentDto result = service.create(dto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    ResponseEntity<DepartmentDto> update(@Valid @RequestBody DepartmentDto dto){
        try{
            service.update(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping()
    ResponseEntity<DepartmentDto> delete(@Valid @RequestBody DepartmentDto dto){
        try{
            service.delete(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
