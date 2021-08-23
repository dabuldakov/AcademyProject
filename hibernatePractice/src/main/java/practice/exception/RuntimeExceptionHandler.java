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

//    @ResponseBody
//    @ExceptionHandler(WeatherBadResponse.class)
//    @ResponseStatus(HttpStatus.OK)
//    String weatherBadResponse(WeatherBadResponse e){
//        return e.getMessage();
//    }
}
