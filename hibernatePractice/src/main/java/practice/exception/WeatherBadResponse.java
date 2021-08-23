package practice.exception;

public class WeatherBadResponse extends Exception{
    public WeatherBadResponse(String message) {
        super(message);
    }
}
