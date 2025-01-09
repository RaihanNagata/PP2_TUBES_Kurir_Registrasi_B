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
import model.User;

/**
 *
 * @author Haida
 */
public class UserDao {
  public int insert(User user) {
    int result = -1;
    try (Connection connection = MySqlConnection.getInstance().getConnection();) {
      PreparedStatement statement = connection.prepareStatement(
          "insert into users (username, email, password, nama, jenisKelamin, alamat) values (?, ?, ?, ?, ?, ?)");
      statement.setString(1, user.getUsername());
      statement.setString(2, user.getEmail());
      statement.setString(3, user.getPassword());
      statement.setString(4, user.getNama());
      statement.setString(5, user.getJenisKelamin());
      statement.setString(6, user.getAlamat());

      result = statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  public int update(User user) {
    int result = -1;
    try (Connection connection = MySqlConnection.getInstance().getConnection();) {
      PreparedStatement statement = connection.prepareStatement(
          "update users set nama = ? where id = ?");
      statement.setString(1, user.getNama());
      statement.setString(1, user.getId());

      result = statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  public int delete(User user) {
    int result = -1;
    try (Connection connection = MySqlConnection.getInstance().getConnection();) {
      PreparedStatement statement = connection.prepareStatement("delete from users where id = ?");
      statement.setString(1, user.getId());
      result = statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  public List<User> findAll() {
    List<User> list = new ArrayList<>();
    try (Connection connection = MySqlConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();) {
      try (ResultSet resultSet = statement.executeQuery("select * from users");) {
        while (resultSet.next()) {
          User user = new User();
          user.setNama(resultSet.getString("nama"));
          user.setEmail(resultSet.getString("email"));
          user.setAlamat(resultSet.getString("alamat"));
          list.add(user);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }
}
