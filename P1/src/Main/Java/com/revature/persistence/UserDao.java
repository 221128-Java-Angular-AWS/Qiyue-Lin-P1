package com.revature.persistence;

import com.revature.exceptions.PasswordIncorrectException;
import com.revature.exceptions.UserAlreadyExistException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.pojos.User;

import java.io.StringReader;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class UserDao {
    private Connection connection;

    public UserDao(){
        this.connection = ConnectionManager.getConnection();
    }

    public void create(User user) throws UserAlreadyExistException{

        String sql = "SELECT * FROM users WHERE email = ?";
        try {
            //ensure the email is not already registered
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user.getEmail());

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                throw new UserAlreadyExistException("This email was already registered");
            }

            if(user.getRole() == null){
                //Create user with only email and password
                sql = "INSERT INTO users (email, password) VALUES (?, ?)";
                pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1,user.getEmail());
                pstmt.setString(2,user.getPassword());
                pstmt.executeUpdate();
            } else {
                //Create user with a role
                sql = "INSERT INTO users (email, password, role) VALUES (?, ?, ?)";
                pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1,user.getEmail());
                pstmt.setString(2,user.getPassword());
                pstmt.setString(3,user.getRole());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User authenticate(String email, String password) throws  UserNotFoundException, PasswordIncorrectException{
        try{
            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            // should only ever give one result...

            if(!rs.next()){
                throw new UserNotFoundException("This email was not found");
            }

            User user = new User(rs.getInt("user_id"), rs.getString("email"),
                    rs.getString("password"),rs.getString("role"));
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
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());
            pstmt.setInt(3, user.getUserId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
    public void delete(User user) {
        try{
            String sql = "DELETE FROM users WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, user.getUserId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
    /*
    // use for Test
    public Set<User> getAllUsers() {
        Set<User> results = new HashSet<>();
        try {
            String sql = "SELECT * FROM users";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                results.add( new User(rs.getInt("user_id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
*/
}
