package practice.mapper.model;

public class CarDto {
    private int id;
    private String name;
    private Integer price;
    private HumanDto human;

    public HumanDto getHuman() {
        return human;
    }

    public void setHuman(HumanDto human) {
        this.human = human;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CarDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", human=" + human +
                '}';
    }
}
