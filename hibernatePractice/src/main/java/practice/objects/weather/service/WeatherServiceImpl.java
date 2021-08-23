package practice.objects.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice.api.weather.ApiWeather;
import practice.api.weather.model.WeatherResponse;
import practice.exception.NotFoundException;
import practice.exception.WeatherApiDown;
import practice.exception.WeatherBadResponse;
import practice.mapper.Mapper;
import practice.objects.city.City;
import practice.objects.user.User;
import practice.objects.user.dao.UserRepository;
import practice.objects.weather.Weather;
import practice.objects.weather.WeatherConverter;
import practice.objects.weather.WeatherDto;
import practice.objects.weather.dao.WeatherRepository;

import java.util.Date;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WeatherRepository weatherRepository;
    @Autowired
    private WeatherConverter weatherConverter;
    @Autowired
    private ApiWeather apiWeather;
    @Autowired
    private Mapper mapper;

    @Override
    public WeatherDto getWeatherByUser(String userName) {
        User user = userRepository.findUserByName(userName);
        if (user == null)
            throw new NotFoundException(userName + " not found in DB.");
        if (user.getCity() == null)
            throw new NotFoundException(userName + " has no City in DB.");

        try {
            WeatherResponse weatherResponse = apiWeather.callWeatherApi(user.getCity().getName());
            WeatherDto weatherDto = weatherConverter.weatherResponseToDto(weatherResponse);
            weatherDto.setTemperatureDate(new Date());
            saveWeatherInDb(weatherDto, user.getCity());
            return weatherDto;
        }catch (WeatherBadResponse | WeatherApiDown e){
            e.printStackTrace();
            Weather weatherByCity = weatherRepository.getWeatherByCity(user.getCity());
            if(weatherByCity != null) {
                WeatherDto weatherDto = mapper.convert(weatherByCity, WeatherDto.class);
                weatherDto.setName(weatherByCity.getCity().getName());
                return weatherDto;
            }else {
                throw new NotFoundException("Service down and temperature not found for city " + user.getCity().getName() + " in DB.");
            }
        }
    }

    private void saveWeatherInDb(WeatherDto weatherDto, City city) {
        Weather weatherByCity = weatherRepository.getWeatherByCity(city);
        if (weatherByCity != null) {
            weatherByCity.setTemperatureDate(new Date());
            weatherRepository.save(weatherByCity);
        } else {
            Weather weather = mapper.convert(weatherDto, Weather.class);
            weather.setCity(city);
            weather.setTemperatureDate(new Date());
            weatherRepository.save(weather);
        }
    }
}
