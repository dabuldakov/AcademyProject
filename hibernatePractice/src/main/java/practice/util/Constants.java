package practice.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public abstract class Constants {
    public static final String URL = "jdbc:postgresql://localhost:5432/";
    public static final String DATABASE = "postgres";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "ChangePassword";
    public static final String PERSON_TABLE = "CREATE TABLE \"publisher\".person " +
            "(id SERIAL PRIMARY KEY, " +
            "first_name VARCHAR(30) NOT NULL, " +
            "second_name VARCHAR(30) NOT NULL, " +
            "birthday DATE NOT NULL, " +
            "department_id INTEGER REFERENCES \"publisher\".department (id) NOT NULL)";
    public static final String DEPARTMENT_TABLE = "CREATE TABLE \"publisher\".department " +
            "(id SERIAL PRIMARY KEY, " +
            "name VARCHAR(50) NOT NULL, " +
            "UNIQUE(name))";
    public static final String PERSON_TABLE_INSERT = "INSERT INTO \"publisher\".person (first_name, second_name, birthday, department_id) VALUES" +
            "('Nikolay', 'Bondarchuk', '1985-12-22', 1), " +
            "('Alexandr', 'Selyanin', '1986-01-02', 1), " +
            "('Aleksey', 'lee', '1985-12-27', 2), " +
            "('Vitaly', 'Eirich', '1986-01-01', 1), " +
            "('Natasha', 'Tarasova', '1984-01-30', 2)";
    public static final String DEPARTMENT_TABLE_INSERT = "INSERT INTO \"publisher\".department (name) VALUES " +
            "('Java developers department'), " +
            "('Java QA department')";

    public static final String GET_PERSON_BY_ID = "SELECT p.id, p.first_name, p.second_name, p.birthday, d.id as department_id, d.name as department_name " +
            "from person p left join department d on p.department_id = d.id " +
            "where p.id = ?";
    public static final String GET_DEPARTMENT_BY_ID = "select id, name from department where id = ?";
    public static final String INSERT_DEPARTMENT = "INSERT INTO department(name) VALUES (?)";
    public static final String INSERT_PERSON = "INSERT INTO person(first_name, second_name, birthday, department_id) VALUES (?, ?, ?, ?)";
    public static final String DELETE_PERSON = "DELETE FROM person WHERE id = ?";
    public static final String DELETE_DEPARTMENT = "DELETE FROM department WHERE id = ?";
    public static final String UPDATE_PERSON = "UPDATE person SET first_name=?, second_name=?, birthday=?, department_id=? WHERE id =?";
    public static final String UPDATE_DEPARTMENT = "UPDATE department SET name=? WHERE id = ?";

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
}