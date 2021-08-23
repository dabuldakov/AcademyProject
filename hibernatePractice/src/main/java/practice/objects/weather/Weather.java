package practice.objects.weather;

import practice.objects.city.City;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "weather", schema = "publisher")
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id", foreignKey = @ForeignKey(name = "city_id_fk"),
            nullable = false, unique = true)
    private City city;

    @Column(name = "temperature")
    private double temperature;

    @Column(name = "temperature_date")
    private Date temperatureDate;

    public Weather() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public Date getTemperatureDate() {
        return temperatureDate;
    }

    public void setTemperatureDate(Date temperatureDate) {
        this.temperatureDate = temperatureDate;
    }
}
