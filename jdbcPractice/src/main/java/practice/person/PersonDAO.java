package practice.person;

import practice.Constants;
import practice.department.Department;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@Component
public class PersonDAO {

    public PersonDAO() {
    }

    public Person getPersonById(int id) {
        try (Connection connection = DriverManager.getConnection(Constants.URL + Constants.DATABASE, Constants.USERNAME, Constants.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(Constants.GET_PERSON_BY_ID)) {
            connection.setSchema("publisher");
            statement.setInt(1, id);
            ResultSet personResultSet = statement.executeQuery();
            if (personResultSet.next()) {
                Person person = new Person();
                person.setId(personResultSet.getInt(1));
                person.setFirstName(personResultSet.getString(2));
                person.setSecondName(personResultSet.getString(3));
                person.setBirthday(Constants.DATE_FORMAT.parse(personResultSet.getDate(4).toString()));
                Department department = new Department();
                department.setId(personResultSet.getInt(5));
                department.setName(personResultSet.getString(6));
                person.setDepartment(department);
                return person;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertPerson(Person person) {
        try (Connection connection = DriverManager.getConnection(Constants.URL + Constants.DATABASE, Constants.USERNAME, Constants.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(Constants.INSERT_PERSON, RETURN_GENERATED_KEYS)) {
            connection.setSchema("publisher");
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getSecondName());
            statement.setDate(3, Date.valueOf(Constants.DATE_FORMAT.format(person.getBirthday())));
            statement.setInt(4, person.getDepartment().getId());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                person.setId(resultSet.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertPersons(ArrayList<Person> list) {
        try (Connection connection = DriverManager.getConnection(Constants.URL + Constants.DATABASE, Constants.USERNAME, Constants.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(Constants.INSERT_PERSON, RETURN_GENERATED_KEYS)) {
            connection.setSchema("publisher");
            connection.setAutoCommit(false);

            for (Person person : list) {
                statement.setString(1, person.getFirstName());
                statement.setString(2, person.getSecondName());
                statement.setDate(3, Date.valueOf(Constants.DATE_FORMAT.format(person.getBirthday())));
                statement.setInt(4, person.getDepartment().getId());
                statement.addBatch();
            }
            statement.executeBatch();
            connection.commit();
            ResultSet resultSet = statement.getGeneratedKeys();
            for (Person person : list) {
                resultSet.next();
                person.setId(resultSet.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean deletePerson(int id) {
        try (Connection connection = DriverManager.getConnection(Constants.URL + Constants.DATABASE, Constants.USERNAME, Constants.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(Constants.DELETE_PERSON)) {
            connection.setSchema("publisher");
            statement.setInt(1, id);
            statement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deletePersons(ArrayList<Person> list) {
        try (Connection connection = DriverManager.getConnection(Constants.URL + Constants.DATABASE, Constants.USERNAME, Constants.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(Constants.DELETE_PERSON)) {
            connection.setSchema("publisher");
            connection.setAutoCommit(false);
            for (Person person : list) {
                statement.setInt(1, person.getId());
                statement.addBatch();
            }
            statement.executeBatch();
            connection.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updatePerson(Person person) {
        try (Connection connection = DriverManager.getConnection(Constants.URL + Constants.DATABASE, Constants.USERNAME, Constants.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(Constants.UPDATE_PERSON)) {
            connection.setSchema("publisher");
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getSecondName());
            statement.setDate(3, Date.valueOf(Constants.DATE_FORMAT.format(person.getBirthday())));
            statement.setInt(4, person.getDepartment().getId());
            statement.setInt(5, person.getId());
            statement.execute();
            Person personById = getPersonById(person.getId());
            System.out.println("Updated practice.person: " + personById);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
