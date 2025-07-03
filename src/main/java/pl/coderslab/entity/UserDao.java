package pl.coderslab.entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public void create(User user) {
        String query = "INSERT INTO " + DbUtil.getTableName() + " (email, username, password) VALUES (?, ?, ?)";
        try (Connection conn = DbUtil.connect()) {
            DbUtil.insert(conn, query, user.getEmail(), user.getUserName(), user.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public User read(int id) {
        String query = "SELECT * FROM " + DbUtil.getTableName() + " WHERE id = " + id;
        try (Connection conn = DbUtil.connect(); PreparedStatement statement = conn.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User(resultSet.getInt("id"), resultSet.getString("email"), resultSet.getString("username"), resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(User user) {
        String query = "UPDATE " + DbUtil.getTableName() + " SET email ='" + user.getEmail() + "', username='" + user.getUserName() + "', password='" + user.getPassword() + "' WHERE id = " + user.getId();
        try (Connection conn = DbUtil.connect(); PreparedStatement statement = conn.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection conn = DbUtil.connect()) {
            DbUtil.remove(conn, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM " + DbUtil.getTableName();
        try (Connection conn = DbUtil.connect(); PreparedStatement statement = conn.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User(resultSet.getInt("id"), resultSet.getString("email"), resultSet.getString("username"), resultSet.getString("password"));
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
