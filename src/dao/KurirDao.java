/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.MySqlConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Kurir;

/**
 *
 * @author Haida
 */
public class KurirDao {
    private final MySqlConnection mySqlConnection;
    
    public KurirDao() {
        this.mySqlConnection = new MySqlConnection();
    }
    
    public int insert(Kurir kurir) {
        int result = -1;
        try (Connection connection = mySqlConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("insert into kurir (id, nama, email, no_telepon, alamat, tanggal_registrasi) values (?, ?, ?, ?, ?, ?)");
            statement.setString(1, kurir.getId());
            statement.setString(2, kurir.getNama());
            statement.setString(3, kurir.getEmail());
            statement.setString(4, kurir.getNoTelp());
            statement.setString(5, kurir.getAlamat());
            statement.setString(6, kurir.getTglRegis());
            
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public int update(Kurir kurir) {
        int result = -1;
        try (Connection connection = mySqlConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("update kurir set nama = ?, email = ?, no_telepon = ?, alamat = ?, tanggal_registrasi = ? where id = ?");
            statement.setString(1, kurir.getNama());
            statement.setString(2, kurir.getEmail());
            statement.setString(3, kurir.getNoTelp());
            statement.setString(4, kurir.getAlamat());
            statement.setString(5, kurir.getTglRegis());
            statement.setString(6, kurir.getId());
            
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public int delete(Kurir kurir) {
        int result = -1;
        try (Connection connection = mySqlConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("delete from kurir where id = ?");
            statement.setString(1, kurir.getId());
            
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public List<Kurir> findAll() {
        List<Kurir> list = new ArrayList<>();
        try (Connection connection = mySqlConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from kurir")) {
            
            while(resultSet.next()) {
                Kurir kurir = new Kurir();
                kurir.setId(resultSet.getString("id"));
                kurir.setNama(resultSet.getString("nama"));
                kurir.setEmail(resultSet.getString("email"));
                kurir.setNoTelp(resultSet.getString("no_telepon"));
                kurir.setAlamat(resultSet.getString("alamat"));
                kurir.setTglRegis(resultSet.getString("tanggal_registrasi"));
                list.add(kurir);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
