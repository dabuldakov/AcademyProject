package reflection;

public class House {
    private int id;
    private String location;

    public House(int id, String location) {
        this.id = id;
        this.location = location;
    }

    public House() {
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", location='" + location + '\'' +
                '}';
    }
}
