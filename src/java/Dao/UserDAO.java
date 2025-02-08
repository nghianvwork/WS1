/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dao.DBContext;
import Model.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import Model.Users;

/**
 *
 * @author Legion
 */
public class UserDAO extends DBContext {

    Connection conn;

    public UserDAO() {
        try {
            conn = getConnection();
        } catch (Exception e) {
            System.out.println("Connect Failed");
        }
    }

    public void updatePassword(Users us) {
        String sql = "update Users set PasswordHash = ? where UserID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, us.getPasswordHash());
            pre.setInt(2, us.getUserID());
            pre.executeUpdate();
        } catch (SQLException e) {

        }
    }
    private static final String SQL_SELECT_BY_USERNAME = "SELECT * FROM Users WHERE username = ?";

    public Users getUserByUsername(String username) {
        Users user = null;
        try (PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BY_USERNAME)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = extractUserFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println("getUsersByUsername: " + e.getMessage());
        }
        return user;
    }

    public Users getUserByEmail(String email) {
        Users user = null;
        String sql = "SELECT * FROM Users WHERE Email = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = extractUserFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println("getUserByEmail: " + e.getMessage());
        }
        return user;
    }

    public void addUser(Users user) {
        String sql = "INSERT INTO Users (Username, PasswordHash, Email, FullName, Role, RegistrationDate) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPasswordHash());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getFullName());
            statement.setString(5, user.getRole());
            statement.setTimestamp(6, java.sql.Timestamp.valueOf(user.getRegistrationDate()));
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("addUsers: " + e.getMessage());
        }
    }

    private Users extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        Users user = new Users();
        user.setUserID(resultSet.getInt("UserID"));
        user.setUsername(resultSet.getString("Username"));
        user.setPasswordHash(resultSet.getString("PasswordHash"));
        user.setEmail(resultSet.getString("Email"));
        user.setFullName(resultSet.getString("FullName"));
        user.setRole(resultSet.getString("Role"));
        user.setRegistrationDate(resultSet.getTimestamp("RegistrationDate").toLocalDateTime());
        return user;
    }

   
    public Users getUserByID(int userID) {
        Users user = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Userss WHERE UserID = ?")) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = extractUserFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println("getUserByUsersID: " + e.getMessage());
        }
        return user;
    }
}
