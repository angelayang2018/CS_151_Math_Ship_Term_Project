package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneControl {
	
	private Stage stage;
	private Scene scene;
	private Parent root; 
	
	
	
	
	public void toMainScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	public void toLoginScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	public void toSignUpScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("SignUpScene.fxml"));
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	public void toPlayMenuScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("PlayMenu.fxml"));
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	public void toGamePlay(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("GamePlay.fxml"));
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	public void toScoreboard(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("ScoreboardScene.fxml"));
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	public void toHelp(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("HelpScene.fxml"));
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
