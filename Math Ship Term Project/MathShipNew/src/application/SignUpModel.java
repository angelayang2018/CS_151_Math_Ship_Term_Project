package application;

import java.sql.*;

public class SignUpModel {
    Connection connection;

    public SignUpModel() {
        connection = DatabaseConnection.Connector();
        if (connection == null) {

            System.out.println("connection not successful");
            System.exit(1);
        }
    }

    public boolean isSignUp(String user, String pass) throws SQLException {
        PreparedStatement checkIfUserExists = null;
        PreparedStatement insertUser = null;
        ResultSet resultSet = null;
        String query = "select * from users where username = ?";
        try {
            checkIfUserExists = connection.prepareStatement(query);
            checkIfUserExists.setString(1, user);
            //checkIfUserExists.setString(2, pass);

            resultSet = checkIfUserExists.executeQuery();
            if (resultSet.isBeforeFirst() || user == "" || pass == "") {
                System.out.println("Invalid username or password.");
                return false;
            }
             else {
                query = "INSERT INTO users (username, password) VALUES(?, ?)";
                insertUser = connection.prepareStatement(query);
                insertUser.setString(1, user);
                insertUser.setString(2, pass);
                insertUser.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            checkIfUserExists.close();
            resultSet.close();
        }
    }

    public boolean isDbConnected() {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}