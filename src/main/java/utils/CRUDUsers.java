package utils;

import entities.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CRUDUsers {
    private static final String GET_ALL_USERS = "SELECT * from users";
    private static final String GET_SOME_USER = "SELECT * from users WHERE id = ?";
    private static final String INSERT_USER = "INSERT INTO users (name, surname, login, password) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_PASSWORD_USER = "UPDATE users SET password = ? WHERE id = ?";
    private static final String DELETE_ALL_USER = "DELETE from users";
    private static final String DELETE_SOME_USER = "DELETE from users WHERE id = ?";

    public static List<Users> getUsers() {
        List<Users> users = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("User not found");
                return null;
            } else {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    String login = resultSet.getString("login");
                    String password = resultSet.getString("password");

                    users.add(new Users(id, name, surname, login, password));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public static Users getSomeUser(int userId) {
        Users user = new Users();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_SOME_USER)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("User not found");
                return null;
            } else {
                while (resultSet.next()) {

                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setSurname(resultSet.getString("surname"));
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void addUser(Users user) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updatePasswordUser(String value, int userId) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD_USER)) {
            preparedStatement.setString(1, value);
            preparedStatement.setInt(2, userId);
            if (preparedStatement.executeUpdate() == 0) {
                System.out.println("User ID " + userId + " not found");
            } else {
                System.out.println("User ID " + userId + " password updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAllUser() {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_USER)) {
            if (preparedStatement.executeUpdate() == 0) {
                System.out.println("Table is empty");
            } else {
                System.out.println("All users deleted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteSomeUser(int id) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SOME_USER)) {
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() == 0) {
                System.out.println("User ID " + id + " not found");
            } else {
                System.out.println("User ID " + id + " deleted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
