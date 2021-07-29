package practice.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import practice.Response;
import practice.Statuses;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("person")
public class PersonController {

    private static final String KEY = "strong_password";

    @Autowired
    PersonService service;

    @GetMapping(value = "{id}")
    PersonDto getById(@RequestHeader(value = "access_key") String accessKey, @PathVariable  int id){
        PersonDto result = new PersonDto();
        if (accessKey.equals(KEY)) {
            result = service.find(id);
        }
        return result;
    }

    @GetMapping()
    List<PersonDto> getAll(@RequestHeader(value = "access_key") String accessKey){
        List<PersonDto> result = new ArrayList<>();
        if (accessKey.equals(KEY)) {
            List<PersonDto> all = service.findAll();
        }
        return result;
    }

    @PostMapping()
    Response create(@RequestHeader(value = "access_key") String accessKey, @RequestBody PersonDto personDTO){

        Response response = new Response();
        if (accessKey.equals(KEY)){
            try {
                PersonDto result = service.create(personDTO);
                response.setObject(result);
                response.setStatus(Statuses.SUCCESS);
            }catch (Exception e){
                response.setStatus(Statuses.FAIL);
                response.setDescription(e.getMessage());
            }
        }
        return response;
    }
    @PutMapping
    Response update(@RequestHeader(value = "access_key") String accessKeyString, @RequestBody PersonDto personDTO){
        Response response = new Response();
        if (accessKeyString.equals(KEY)){
            try {
                boolean result = service.update(personDTO);
                response.setObject(result);
                response.setStatus(Statuses.SUCCESS);
            }catch (Exception e){
                response.setStatus(Statuses.FAIL);
                response.setDescription(e.getMessage());
            }
        }
        return response;
    }

    @DeleteMapping
    Response delete(@RequestHeader(value = "access_key") String accessKey, @RequestBody PersonDto personDTO){
        Response response = new Response();
        if (accessKey.equals(KEY)){
            try {
                boolean result = service.delete(personDTO);
                response.setObject(result);
                response.setStatus(Statuses.SUCCESS);
            }catch (Exception e){
                response.setStatus(Statuses.FAIL);
                response.setDescription(e.getMessage());
            }
        }
        return response;
    }
}
