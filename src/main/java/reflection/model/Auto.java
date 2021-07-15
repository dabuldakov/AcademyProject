package reflection.model;

public class Auto {
    private String color;
    private String id;

    public Auto(String color, String id) {
        this.color = color;
        this.id = id;
    }

    public Auto() {
    }

    @Override
    public String toString() {
        return "Auto{" +
                "color='" + color + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
