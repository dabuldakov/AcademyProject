package practice.mapper.model;

import java.util.Date;
import java.util.List;

public class DocDto {
    private int id;
    private String number;
    private Date expiryDate;
    private CarDto car;
    private List<CarDto> cars;

    public List<CarDto> getCars() {
        return cars;
    }

    public void setCars(List<CarDto> cars) {
        this.cars = cars;
    }

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "DocDto{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", expiryDate=" + expiryDate +
                ", car=" + car +
                ", cars=" + cars +
                '}';
    }
}
