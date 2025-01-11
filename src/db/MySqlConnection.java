/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Haida
 */
public class MySqlConnection {
<<<<<<< Updated upstream
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
        return instance;
    }
    
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (Exception e) {
            e.printStackTrace();
=======
    private static final String URL = "jdbc:mysql://localhost:3306/TubesPP";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Database berhasil terkoneksi!");
            } catch (ClassNotFoundException e) {
                throw new SQLException("MySQL JDBC Driver tidak ditemukan", e);
            }
>>>>>>> Stashed changes
        }
        return connection;
    }
}
