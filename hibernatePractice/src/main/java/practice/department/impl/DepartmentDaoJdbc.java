package practice.department.impl;

import org.springframework.stereotype.Component;
import practice.util.Constants;
import practice.department.Department;
import practice.department.DepartmentDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@Component
public class DepartmentDaoJdbc implements DepartmentDao {

    public DepartmentDaoJdbc() {
    }

    public Department find(int id) {
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

    public Department create(Department department) {
        try (Connection connection = DriverManager.getConnection(Constants.URL + Constants.DATABASE, Constants.USERNAME, Constants.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(Constants.INSERT_DEPARTMENT, RETURN_GENERATED_KEYS)) {
            connection.setSchema("publisher");
            statement.setString(1, department.getName());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                department.setId(resultSet.getInt(1));
            }
            return department;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createList(ArrayList<Department> list) {
        try (Connection connection = DriverManager.getConnection(Constants.URL + Constants.DATABASE, Constants.USERNAME, Constants.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(Constants.INSERT_DEPARTMENT, RETURN_GENERATED_KEYS)) {
            connection.setSchema("publisher");
            connection.setAutoCommit(false);

            for (Department department : list) {
                statement.setString(1, department.getName());
                statement.addBatch();
            }
            statement.executeBatch();
            connection.commit();
            ResultSet resultSet = statement.getGeneratedKeys();
            for (Department department : list) {
                resultSet.next();
                department.setId(resultSet.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Department department) {
        try (Connection connection = DriverManager.getConnection(Constants.URL + Constants.DATABASE, Constants.USERNAME, Constants.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(Constants.DELETE_DEPARTMENT)) {
            connection.setSchema("publisher");
            statement.setInt(1, department.getId());
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Department> deleteList(ArrayList<Department> list) {
        ArrayList<Department> listReturn = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(Constants.URL + Constants.DATABASE, Constants.USERNAME, Constants.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(Constants.DELETE_DEPARTMENT)) {
            connection.setSchema("publisher");
            connection.setAutoCommit(false);

            for (Department department : list) {
                statement.setInt(1, department.getId());
                statement.addBatch();
            }
            int[] ints = statement.executeBatch();
            int count = 0;
            for (Department department : list) {
                if (ints[count] == 1){
                    listReturn.add(department);
                }
            }
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listReturn;
    }

    public void update(Department department) {
        try (Connection connection = DriverManager.getConnection(Constants.URL + Constants.DATABASE, Constants.USERNAME, Constants.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(Constants.UPDATE_DEPARTMENT)) {
            connection.setSchema("publisher");
            statement.setString(1, department.getName());
            statement.setInt(2, department.getId());
            statement.execute();
            find(department.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Department> updateList(ArrayList<Department> list) {
        ArrayList<Department> listReturn = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(Constants.URL + Constants.DATABASE, Constants.USERNAME, Constants.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(Constants.UPDATE_DEPARTMENT)) {
            connection.setSchema("publisher");
            connection.setAutoCommit(false);

            for (Department department : list) {
                statement.setString(1, department.getName());
                statement.setInt(2, department.getId());
                statement.addBatch();
            }
            int[] updatedList = statement.executeBatch();
            connection.commit();
            int count = 0;
            for (Department department : list) {
                if (updatedList[count] == 1) {
                    listReturn.add(department);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listReturn;
    }

    public ArrayList<Department> getAllWithFetchSize(){

        return null;
    }

    public ArrayList<Department> getAllWithOutFetchSize(){

        return null;
    }

}
