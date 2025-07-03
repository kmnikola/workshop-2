package pl.coderslab.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbUtil {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/workshop2?useSSL=false&characterEncoding=utf8";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "coderslab";
    private static String TABLE_NAME = "users";
    private static final String DELETE_QUERY = "DELETE FROM tableName where id = ?";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

    }

    public static String getTableName() {
        return TABLE_NAME;
    }

    public static void insert(Connection conn, String query, String... params) {
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                statement.setString(i + 1, params[i]);
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void remove(Connection conn, int id) {
        try (PreparedStatement statement =
                     conn.prepareStatement(DELETE_QUERY.replace("tableName", TABLE_NAME));) {
            statement.setInt(1, id);
            int i = statement.executeUpdate();
            if (i == 0) {
                System.out.println("No element to remove under that id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
