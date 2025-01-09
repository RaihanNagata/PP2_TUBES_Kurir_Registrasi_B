package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.MySqlConnection;

public class User {
    private Integer id;
    private String username;
    private String password;
    private String nama;
    private String email;
    private String jenisKelamin;
    private String alamat;

    // Constructor
    public User() {}
    
    // Getters
    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public static User authenticate(String userInput, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = MySqlConnection.getConnection();
            String query = "SELECT * FROM users WHERE (username = ? OR email = ?) AND password = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userInput);
            pstmt.setString(2, userInput);
            pstmt.setString(3, password);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setNama(rs.getString("nama"));
                user.setEmail(rs.getString("email"));
                user.setJenisKelamin(rs.getString("jenisKelamin"));
                user.setAlamat(rs.getString("alamat"));
                return user;
            }
        } catch (SQLException e) {
            System.err.println("Error pada autentikasi: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.err.println("Error menutup resources: " + e.getMessage());
            }
        }
        return null;
    }

    public boolean save() {
        Connection conn = null;
        PreparedStatement checkStmt = null;
        PreparedStatement insertStmt = null;
        ResultSet rs = null;
        
        try {
            conn = MySqlConnection.getConnection();
            conn.setAutoCommit(false); // Mulai transaksi
            
            // Cek duplikat
            String checkQuery = "SELECT username, email FROM users WHERE username = ? OR email = ?";
            checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, this.username);
            checkStmt.setString(2, this.email);
            rs = checkStmt.executeQuery();
            
            if (rs.next()) {
                if (this.username.equals(rs.getString("username"))) {
                    System.out.println("Username sudah terdaftar");
                    return false;
                }
                if (this.email.equals(rs.getString("email"))) {
                    System.out.println("Email sudah terdaftar");
                    return false;
                }
            }
            
            // Insert user baru
            String insertQuery = "INSERT INTO users (username, password, nama, email, jenisKelamin, alamat) " +
                               "VALUES (?, ?, ?, ?, ?, ?)";
            insertStmt = conn.prepareStatement(insertQuery);
            insertStmt.setString(1, this.username);
            insertStmt.setString(2, this.password);
            insertStmt.setString(3, this.nama);
            insertStmt.setString(4, this.email);
            insertStmt.setString(5, this.jenisKelamin);
            insertStmt.setString(6, this.alamat);
            
            int result = insertStmt.executeUpdate();
            conn.commit(); // Commit transaksi
            
            if (result > 0) {
                System.out.println("User berhasil disimpan");
                return true;
            }
            return false;
            
        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                System.err.println("Error melakukan rollback: " + ex.getMessage());
            }
            System.err.println("Error menyimpan user: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (checkStmt != null) checkStmt.close();
                if (insertStmt != null) insertStmt.close();
                if (conn != null) conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println("Error menutup resources: " + e.getMessage());
            }
        }
    }
}