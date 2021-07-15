package reflection;

public class Person {
    private String name;
    @JsonParse(name = "telephone")
    private long phoneNumber;
    private House house;

    public Person(String name, long phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", house=" + house +
                '}';
    }
}
