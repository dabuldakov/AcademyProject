package practice.person;

import org.hibernate.annotations.BatchSize;
import practice.department.Department;
import practice.document.Document;
import practice.language.Language;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@NamedQueries({
        @NamedQuery(name = "getAllByFirstName", query = "select p from Person p where p.firstName =: firstName"),
        @NamedQuery(name = "getAllBySecondName", query = "select p from Person p where p.secondName =: secondName")
})

@Entity()
@Table(name = "person", schema = "publisher")
@BatchSize(size = 5)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    private Date birthday;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "department_id", foreignKey = @ForeignKey(name = "department_id_fk"))
    private Department department;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "document_id", foreignKey = @ForeignKey(name = "document_id_fk"), unique = true)
    private Document document;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Language> language;

    public Person() {
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", birthday=" + birthday +
                ", department=" + department +
                ", document=" + document +
                ", languageList=" + language +
                '}';
    }

    public List<Language> getLanguage() {
        return language;
    }

    public void setLanguage(List<Language> languageList) {
        this.language = languageList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(firstName, person.firstName) && Objects.equals(secondName, person.secondName) && Objects.equals(birthday, person.birthday) && Objects.equals(department, person.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, secondName, birthday, department);
    }
}
