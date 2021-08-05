package practice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RuntimeExceptionHandler {

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String notFound(NotFoundException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(IdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String idIncorrect(IdException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(AccessException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String access(AccessException e){
        return e.getMessage();
    }

}
