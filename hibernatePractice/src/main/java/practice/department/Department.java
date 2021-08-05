package practice.department;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "department", schema = "publisher")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
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
