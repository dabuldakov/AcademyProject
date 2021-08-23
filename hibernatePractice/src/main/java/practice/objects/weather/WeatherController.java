package practice.objects.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import practice.objects.weather.service.WeatherService;


@RestController
@RequestMapping("weather")
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @GetMapping()
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @ResponseStatus(HttpStatus.OK)
    WeatherDto getWeatherByUser(Authentication authentication){
        return weatherService.getWeatherByUser((String) authentication.getPrincipal());
    }
}
