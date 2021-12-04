package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ScoreBoardControl implements Initializable {

	private Stage stage;
	private Scene scene;
	private Parent root;
	private String user;

	@FXML
	private TableView<User> scoreboardTableView;
	@FXML
	private TableColumn<User, Integer> rankingTableColumn;
	@FXML
	private TableColumn<User, String> nameTableColumn;
	@FXML
	private TableColumn<User, Integer> scoreTableColumn;
	@FXML
	Label isConnected;

	public ScoreBoardModel scoreBoardModel = new ScoreBoardModel();

	public void toPlayMenuScene(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("view/PlayMenu.fxml"));
		root = loader.load();
		PlayMenuControl playMenuControl = (PlayMenuControl) loader.getController();
		playMenuControl.setUserName(user);
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	ObservableList<User> listM;

	int index = -1;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (scoreBoardModel.isDbConnected()) {
			isConnected.setText("");
		} else {
			isConnected.setText("Not Connected");
		}

		scoreboardTableView.setFixedCellSize(70);

		nameTableColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		scoreTableColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("score"));

		try {
			listM = scoreBoardModel.getDataUsers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		scoreboardTableView.setItems(listM);
	}

	public void setUserName(String user) {
		this.user = user;
	}

}
