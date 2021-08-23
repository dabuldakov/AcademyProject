package practice.objects.weather;

import com.fasterxml.jackson.annotation.JsonFormat;
import practice.util.Constants;

import java.util.Date;

public class WeatherDto {
    private String name;
    private double temperature;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_PATTERN)
    private Date temperatureDate;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTemperatureDate() {
        return temperatureDate;
    }

    public void setTemperatureDate(Date temperatureDate) {
        this.temperatureDate = temperatureDate;
    }
}
