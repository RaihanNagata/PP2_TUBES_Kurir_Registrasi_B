
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MySqlConnection {

    // Tambahkan nama database pada url
    private final static String DB_URL = "jdbc:mysql://localhost:3306/TubesPP";
    // Isi dengan nama user yang memiliki access ke database
    private final static String DB_USER = "root";
    // Isi dengan password milik user tersebut
    private final static String DB_PASS = "root";
    private static Connection connection;

    private static MySqlConnection instance;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                System.out.println("Database berhasil terkoneksi!");
            } catch (ClassNotFoundException e) {
                throw new SQLException("MySQL JDBC Driver tidak ditemukan", e);
            }
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed!");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}
