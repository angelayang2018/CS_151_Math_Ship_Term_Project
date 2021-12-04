package application;

import java.sql.*;

public class LoginModel {
	Connection connection;

	public LoginModel() {
		connection = DatabaseConnection.Connector();
		if (connection == null) {

			System.out.println("connection not successful");
			System.exit(1);
		}
	}

	public boolean isLogin(String user, String pass) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select * from users where username = ? and password = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user);
			preparedStatement.setString(2, pass);

			resultSet = preparedStatement.executeQuery();
			if(user == "" || pass == ""){
				return false;
			}
			else if(resultSet.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		} finally {
			preparedStatement.close();
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