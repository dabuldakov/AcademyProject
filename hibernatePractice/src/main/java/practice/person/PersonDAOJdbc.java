package practice.person;

import org.springframework.stereotype.Component;
import practice.Constants;
import practice.department.Department;
import practice.person.Person;

import java.sql.*;
import java.util.ArrayList;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@Component
public class PersonDAOJdbc implements PersonDAO{

    public PersonDAOJdbc() {
    }

    public Person find(int id) {
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

    public Person create(Person person) {
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
            return person;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Person> createList(ArrayList<Person> list) {
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
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean delete(Person person) {
        try (Connection connection = DriverManager.getConnection(Constants.URL + Constants.DATABASE, Constants.USERNAME, Constants.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(Constants.DELETE_PERSON)) {
            connection.setSchema("publisher");
            statement.setInt(1, person.getId());
            statement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Person> deleteList(ArrayList<Person> list) {
        ArrayList<Person> listReturn = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(Constants.URL + Constants.DATABASE, Constants.USERNAME, Constants.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(Constants.DELETE_PERSON)) {
            connection.setSchema("publisher");
            connection.setAutoCommit(false);
            for (Person person : list) {
                statement.setInt(1, person.getId());
                statement.addBatch();
            }
            int[] ints = statement.executeBatch();
            int count = 0;
            for (Person person : list) {
                if (ints[count] == 1){
                    listReturn.add(person);
                }
            }
            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listReturn;
    }

    public boolean update(Person person) {
        try (Connection connection = DriverManager.getConnection(Constants.URL + Constants.DATABASE, Constants.USERNAME, Constants.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(Constants.UPDATE_PERSON)) {
            connection.setSchema("publisher");
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getSecondName());
            statement.setDate(3, Date.valueOf(Constants.DATE_FORMAT.format(person.getBirthday())));
            statement.setInt(4, person.getDepartment().getId());
            statement.setInt(5, person.getId());
            statement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Person> updateList(ArrayList<Person> list) {
        ArrayList<Person> listReturn = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(Constants.URL + Constants.DATABASE, Constants.USERNAME, Constants.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(Constants.UPDATE_PERSON)) {
            connection.setSchema("publisher");
            connection.setAutoCommit(false);

            for (Person person : list) {
                statement.setString(1, person.getFirstName());
                statement.setString(2, person.getSecondName());
                statement.setDate(3, Date.valueOf(Constants.DATE_FORMAT.format(person.getBirthday())));
                statement.setInt(4, person.getDepartment().getId());
                statement.setInt(5, person.getId());
                statement.addBatch();
            }
            int[] updatedList = statement.executeBatch();
            connection.commit();
            int count = 0;
            for (Person person : list) {
                if (updatedList[count] == 1){
                    listReturn.add(person);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listReturn;
    }

    public ArrayList<Person> getAllWithFetchSize(){

        return null;
    }

    public ArrayList<Person> getAllWithOutFetchSize(){

        return null;
    }
}
