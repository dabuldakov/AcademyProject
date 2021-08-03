package practice.person;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import practice.person.exception.PersonAccessException;
import practice.person.exception.PersonIdException;
import practice.person.exception.PersonNotFoundException;

@ControllerAdvice
public class PersonAdvice {

    @ResponseBody
    @ExceptionHandler(PersonNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String personNotFound(PersonNotFoundException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(PersonIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String personIdIncorrect(PersonIdException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(PersonAccessException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String personAccess(PersonAccessException e){
        return e.getMessage();
    }

}
