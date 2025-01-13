package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.MySqlConnection;

public class User {
    private int id;
    private String username;
    private String name;
    private String email;
    private String password;
    private String noTelp;
    private String jenisKelamin;
    private String alamat;
    private String ktp;
    private String kk;

    // Constructor
    public User() {}
    
    // Getters
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNoTelp() {
        return noTelp;
    }
    
    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public String getAlamat() {
        return alamat;
    }
    
    public String getKtp() {
        return ktp;
    }
    
    public String getKk() {
        return ktp;
    }

    // Setters
    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
    public void setKtp(String ktp) {
        this.ktp = ktp;
    }
    
    public void setKk(String kk) {
        this.kk = kk;
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
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setNoTelp(rs.getString("no_telp"));
                user.setJenisKelamin(rs.getString("jenis_kelamin"));
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
            String insertQuery = "INSERT INTO users (username, name, email, password, no_telp, jenis_kelamin, alamat) " +
                               "VALUES (?, ?, ?, ?, ?, ?, ?)";
            insertStmt = conn.prepareStatement(insertQuery);
            insertStmt.setString(1, this.username);
            insertStmt.setString(2, this.name);
            insertStmt.setString(3, this.email);
            insertStmt.setString(4, this.password);
            insertStmt.setString(5, this.noTelp);
            insertStmt.setString(6, this.jenisKelamin);
            insertStmt.setString(7, this.alamat);

            
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
    
    public void updateFoto(int id, String jenisFoto, String foto) {
        Connection conn = null;
        PreparedStatement updateStmt = null;
        ResultSet rs = null;
        String updateQuery;
        
        try {
            conn = MySqlConnection.getConnection();
            conn.setAutoCommit(false); // Mulai transaksi
            if (jenisFoto.equalsIgnoreCase("kk")) {
                updateQuery = "UPDATE users SET kk = ? WHERE id = ?";
            } else if (jenisFoto.equalsIgnoreCase("ktp")) {
                updateQuery = "UPDATE users SET ktp = ? WHERE id = ?";
            } else {
                updateQuery = "UPDATE users SET ktp = ? WHERE id = ?";
            }
            updateStmt = conn.prepareStatement(updateQuery);
            updateStmt.setString(1, foto);
            updateStmt.setString(2, Integer.toString(id));
            
            int result = updateStmt.executeUpdate();
            conn.commit(); // Commit transaksi
            
            if (result > 0) {
                System.out.println("Path file gambar berhasil disimpan");
            }
        } catch(SQLException e) {
            System.err.println("Error: " + e);
        }
    }
}