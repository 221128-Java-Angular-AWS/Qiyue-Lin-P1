package com.revature.persistence;

import com.revature.exceptions.PasswordIncorrectException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.pojos.User;

import java.io.StringReader;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

/*
 * get generated keys
 * CRUD - Create, Read, Update, Delete
 *
 *
 * validation - part of the service layer
 */
public class UserDao {
    private Connection connection;

    public UserDao(){
        this.connection = ConnectionManager.getConnection();
    }

    public void create(User user){
        String sql = "INSERT INTO user (username, password, role) VALUES (?, ?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,user.getUserName());
            pstmt.setString(2,user.getPassword());
            pstmt.setString(3,user.getRole());

            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                user.setUserId(rs.getInt("user_id"));
                System.out.println("DEBUG - auto generated key:" + user.getUserId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User authenticate(String username, String password) throws  UserNotFoundException, PasswordIncorrectException{
        try{
            String sql = "SELECT * FROM user WHERE username = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            // should only ever give one result...

            if(!rs.next()){
                throw new UserNotFoundException("This username was not found");
            }

            User user = new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"), rs.getString("role"));
            if(user.getPassword().equals(password)){
                return user;
            }
            throw new PasswordIncorrectException("Wrong password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void update(User user) {
        try{
            String sql = "UPDATE users SET username = ?, password = ? WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getPassword());
            pstmt.setInt(3, user.getUserId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(User user) {
        try{
            String sql = "DELETE FROM users WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, user.getUserId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Set<User> getAllUsers() {
        try{
            String sql = "SELECT * FROM user";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            Set<User> results = new HashSet<User>();
            while(rs.next()){
                User user = new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"), rs.getString("role"));
                results.add(user);
            }
            return results;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
