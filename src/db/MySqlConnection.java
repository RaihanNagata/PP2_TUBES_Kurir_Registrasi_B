/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Haida
 */
public class MySqlConnection {

    // Tambahkan nama database pada url
    private final static String DB_URL = "jdbc:mysql://localhost:3306/nama_database";
    // Isi dengan nama user yang memiliki access ke database
    private final static String DB_USER = "root";
    // Isi dengan password milik user tersebut
    private final static String DB_PASS = "";
    
    private static MySqlConnection instance;
    
    public static MySqlConnection getInstance() {
        if (instance == null) {
            instance = new MySqlConnection();
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (Exception e) {
            e.printStackTrace();
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed!");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}
