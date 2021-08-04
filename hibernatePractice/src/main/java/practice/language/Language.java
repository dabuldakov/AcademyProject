package practice.language;

import practice.person.Person;
import practice.valid.Letters;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity()
@Table(name = "language", schema = "publisher")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Letters
    private String name;

    public Language() {
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

    @Override
    public String toString() {
        return "Language{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
