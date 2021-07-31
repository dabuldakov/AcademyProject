package practice.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    DepartmentService service;

    @GetMapping("{id}")
    ResponseEntity<DepartmentDto> getById(@PathVariable int id){
        try{
            DepartmentDto dto = service.find(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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

    @PostMapping()
    ResponseEntity<DepartmentDto> create(@RequestBody DepartmentDto dto){
        try{
            DepartmentDto result = service.create(dto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    ResponseEntity<DepartmentDto> update(@RequestBody DepartmentDto dto){
        try{
            service.update(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping()
    ResponseEntity<DepartmentDto> delete(@RequestBody DepartmentDto dto){
        try{
            service.delete(dto);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
