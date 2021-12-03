package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpControl implements Initializable{
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	TextField usernameTextField;
	@FXML
	TextField passwordPasswordField;
	@FXML
	Button signUpButton;
	@FXML
	Label isConnected;

	SignUpModel signUpModel = new SignUpModel();
	
	public void toMainScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("view/MainScene.fxml"));
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	public void inputCredentials(ActionEvent event){
		try {
			if (signUpModel.isSignUp(usernameTextField.getText(), passwordPasswordField.getText())) {
				root = FXMLLoader.load(getClass().getResource("view/LoginScene.fxml"));
				stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			} else {
				isConnected.setText("Username is taken.");
			}
		} catch (Exception e) {
			System.out.println(e);
			isConnected.setText("Exception occurred! Try again later. ");
		}
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (signUpModel.isDbConnected()) {
			isConnected.setText("");
		} else {
			isConnected.setText("Database is not connected.");
		}
		
	}
	

}
