package practice.department;

public class Department {
    private int id;
    private String name;

    public Department() {
    }

    @Override
    public String toString() {
        return "practice.department.Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
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
}
