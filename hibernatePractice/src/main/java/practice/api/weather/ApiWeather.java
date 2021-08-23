package practice.api.weather;

import org.springframework.stereotype.Component;
import practice.api.weather.model.WeatherResponse;
import practice.exception.WeatherApiDown;
import practice.exception.WeatherBadResponse;
import practice.objects.weather.service.JsonParserGson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class ApiWeather {

    private final static String API_KEY = "d52ccce9c99292c58fa31df066596f18";

    public WeatherResponse callWeatherApi(String cityName) throws WeatherBadResponse, WeatherApiDown {
        try {
            URI uri = new URI("http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + API_KEY);
            HttpRequest request = HttpRequest
                    .newBuilder(uri)
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return JsonParserGson.jsonToObject(response.body(), WeatherResponse.class);
            } else {
                throw new WeatherBadResponse("Foreign weather service bad response, status: " + response.statusCode());
            }
        } catch (URISyntaxException | InterruptedException | IOException e) {
            e.printStackTrace();
            throw new WeatherApiDown("Foreign weather service try down.");
        }
    }
}
