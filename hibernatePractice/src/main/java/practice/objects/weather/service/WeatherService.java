package practice.objects.weather.service;

import practice.objects.weather.WeatherDto;

public interface WeatherService {
    public WeatherDto getWeatherByUser(String userName);
}
