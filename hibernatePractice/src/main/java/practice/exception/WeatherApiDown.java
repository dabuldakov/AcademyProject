package practice.exception;

public class WeatherApiDown extends Exception{
    public WeatherApiDown(String message) {
        super(message);
    }
}
