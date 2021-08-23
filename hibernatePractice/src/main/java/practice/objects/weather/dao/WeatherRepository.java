package practice.objects.weather.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.objects.city.City;
import practice.objects.weather.Weather;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
    public Weather getWeatherByCity(City city);
}
