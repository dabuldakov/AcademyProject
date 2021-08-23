package practice.api.weather.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class Main{
	private double temp;
	@JsonProperty("temp_min")
	private double tempMin;
	@JsonProperty("grnd_level")
	private int grndLevel;
	private int humidity;
	private int pressure;
	@JsonProperty("sea_level")
	private int seaLevel;
	@JsonProperty("feels_like")
	private double feelsLike;
	@JsonProperty("temp_max")
	private double tempMax;

	public double getTemp(){
		return temp;
	}

	public double getTempMin(){
		return tempMin;
	}

	public int getGrndLevel(){
		return grndLevel;
	}

	public int getHumidity(){
		return humidity;
	}

	public int getPressure(){
		return pressure;
	}

	public int getSeaLevel(){
		return seaLevel;
	}

	public double getFeelsLike(){
		return feelsLike;
	}

	public double getTempMax(){
		return tempMax;
	}
}
