/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.MySqlConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 *
 * @author Haida
 */
public class UserDao {
  public int insert(User user) {
    int result = -1;

    try (Connection connection = MySqlConnection.getConnection();) {
      PreparedStatement statement = connection.prepareStatement(
          "insert into users (username, name, email, password, no_telp, jenis_kelamin, alamat) values (?, ?, ?, ?, ?, ?)");
      statement.setString(1, user.getUsername());
      statement.setString(2, user.getName());
      statement.setString(3, user.getEmail());
      statement.setString(4, user.getPassword());
      statement.setString(5, user.getNoTelp());
      statement.setString(6, user.getJenisKelamin());
      statement.setString(7, user.getAlamat());

      result = statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  public int update(User user) {
    int result = -1;
    
    try (Connection connection = MySqlConnection.getConnection();) {
      PreparedStatement statement = connection.prepareStatement(
          "update users set username = ?, name = ?, email = ?, password = ?, no_telp = ?, jenis_kelamin = ?, alamat = ?, ktp = ?, kk = ? where id = ?");
      statement.setString(1, user.getUsername());
      statement.setString(2, user.getName());
      statement.setString(3, user.getEmail());
      statement.setString(4, user.getPassword());
      statement.setString(5, user.getNoTelp());
      statement.setString(6, user.getJenisKelamin());
      statement.setString(7, user.getAlamat());
      statement.setString(8, user.getKtp());
      statement.setString(9, user.getKk());
      statement.setString(10, Integer.toString(user.getId()));

      result = statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  public int delete(User user) {
    int result = -1;

    try (Connection connection = MySqlConnection.getConnection();) {
      PreparedStatement statement = connection.prepareStatement("delete from users where id = ?");
      statement.setString(1, Integer.toString(user.getId()));

      result = statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }
  
  public List<User> findAll() {
    List<User> list = new ArrayList<>();
    try (Connection connection = MySqlConnection.getConnection();
        Statement statement = connection.createStatement();) {
      try (ResultSet resultSet = statement.executeQuery("select * from users");) {
        while (resultSet.next()) {
          User user = new User();
          user.setId(resultSet.getString("id"));
          user.setUsername(resultSet.getString("username"));
          user.setName(resultSet.getString("name"));
          user.setEmail(resultSet.getString("email"));
          user.setNoTelp(resultSet.getString("no_telp"));
          user.setJenisKelamin(resultSet.getString("jenis_kelamin"));
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
    
  public List<User> find(int id) {
    List<User> list = new ArrayList<>();
    try (Connection connection = MySqlConnection.getConnection()) {
        PreparedStatement statement = connection.prepareStatement("select * from users where id = ?");
        statement.setString(1, Integer.toString(id));
      try (ResultSet resultSet = statement.executeQuery();) {
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getString("id"));
            user.setUsername(resultSet.getString("username"));
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));
            user.setNoTelp(resultSet.getString("no_telp"));
            user.setJenisKelamin(resultSet.getString("jenis_kelamin"));
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
  
  public int findIdByEmailOrUsername(String emailOrUsername) {
    User user = new User();
    try (Connection connection = MySqlConnection.getConnection()) {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE (username = ? OR email = ?)");
        statement.setString(1, emailOrUsername);
        statement.setString(2, emailOrUsername);
      try (ResultSet resultSet = statement.executeQuery();) {
        if (resultSet.next()) {
            // If a row is found, retrieve the ID and set it to the user object
            user.setId(resultSet.getString("id"));
        } else {
            // No result found, handle the case if needed (e.g., logging)
            System.out.println("No user found with the email: " + emailOrUsername);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return user.getId();
  }
}
