package practice.department;

import org.springframework.stereotype.Component;
import practice.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@Component
public class DepartmentDAO {


    public DepartmentDAO() {
    }

    public Department getDepartmentById(int id) {
        try (Connection connection = DriverManager.getConnection(Constants.URL + Constants.DATABASE, Constants.USERNAME, Constants.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(Constants.GET_DEPARTMENT_BY_ID)) {
            connection.setSchema("publisher");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Department department = new Department();
                department.setId(resultSet.getInt(1));
                department.setName(resultSet.getString(2));
                return department;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createDepartment(Department department) {
        try (Connection connection = DriverManager.getConnection(Constants.URL + Constants.DATABASE, Constants.USERNAME, Constants.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(Constants.INSERT_DEPARTMENT, RETURN_GENERATED_KEYS)) {
            connection.setSchema("publisher");
            statement.setString(1, department.getName());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                department.setId(resultSet.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean deleteDepartment(int id) {
        try (Connection connection = DriverManager.getConnection(Constants.URL + Constants.DATABASE, Constants.USERNAME, Constants.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(Constants.DELETE_DEPARTMENT)) {
            connection.setSchema("publisher");
            statement.setInt(1, id);
            statement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateDepartment(Department department) {
        try (Connection connection = DriverManager.getConnection(Constants.URL + Constants.DATABASE, Constants.USERNAME, Constants.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(Constants.UPDATE_DEPARTMENT)) {
            connection.setSchema("publisher");
            statement.setString(1, department.getName());
            statement.setInt(2, department.getId());
            statement.execute();
            getDepartmentById(department.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
