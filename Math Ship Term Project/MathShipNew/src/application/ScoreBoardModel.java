package application;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ScoreBoardModel {
	Connection connection;

	public ScoreBoardModel() {
		connection = DatabaseConnection.Connector();
		if (connection == null) {

			System.out.println("connection not successful");
			System.exit(1);
		}
	}

	public ObservableList<User> getDataUsers() throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
        ObservableList<User> list = FXCollections.observableArrayList();
        
        
		String query = "select * from scores order by score desc limit 5";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
                list.add(new User(resultSet.getString("username"), Integer.parseInt(resultSet.getString("score"))));
            }
		} catch (Exception e) {
			return null;
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
        return list;
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