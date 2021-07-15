package reflection;

public class House {
    private int id;
    private String location;
    private boolean selling;
    private boolean reappearing;
    private String description;

    public House(int id, String location) {
        this.id = id;
        this.location = location;
    }

    public House() {
    }

    public boolean isReappearing() {
        return reappearing;
    }

    public void setReappearing(boolean reappearing) {
        this.reappearing = reappearing;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isSelling() {
        return selling;
    }

    public void setSelling(boolean selling) {
        this.selling = selling;
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", selling=" + selling +
                ", reappearing=" + reappearing +
                ", description='" + description + '\'' +
                '}';
    }
}
