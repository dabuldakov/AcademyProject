package practice.objects.weather;

import org.springframework.stereotype.Component;
import practice.api.weather.model.WeatherResponse;

@Component
public class WeatherConverter {
    public WeatherDto weatherResponseToDto(WeatherResponse weatherResponse) {
        WeatherDto weatherDto = new WeatherDto();
        weatherDto.setName(weatherResponse.getName());
        if (weatherResponse.getMain() != null) {
            double celsius = fahrenheitToCelsius(weatherResponse.getMain().getTemp());
            weatherDto.setTemperature(celsius);
        }
        return weatherDto;
    }

    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32.0) * 5.0 / 9.0;
    }
}
