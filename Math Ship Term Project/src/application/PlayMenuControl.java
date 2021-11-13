package application;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class PlayMenuControl {
	private Stage stage;
	private Scene scene;
	private Parent root; 
	
	public void startGame(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("view/GameStart.fxml"));
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		GameStart controller = new GameStart();
		scene = new Scene(root);
		scene.setOnKeyPressed(events -> {
				
				switch(events.getCode()) {
				
				case W:
					controller.action("moveUP");
					break;
				case S:
					controller.action("moveDOWN");
					break;
				case A:
					controller.action("moveLEFT");
					break;
				case D:
					controller.action("moveRIGHT");
					break;
				case SPACE:
					controller.action("SHOOT");
					break;
				default:
					break;
				}		
			}
		);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		/**
		root = FXMLLoader.load(getClass().getResource("view/GameStart.fxml"));
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		**/
	}
	
	public void toScoreboard(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("view/ScoreboardScene.fxml"));
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	public void toHelp(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("view/HelpScene.fxml"));
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	public void toMainScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("view/MainScene.fxml"));
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

}
