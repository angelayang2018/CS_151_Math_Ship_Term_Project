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

public class LoginControl implements Initializable {

	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	TextField usernameTextField;
	@FXML
	TextField passwordPasswordField;
	@FXML
	Button loginButton;
	@FXML
	Button backButton;
	@FXML
	Label isConnected;

	public LoginModel loginModel = new LoginModel();

	public void toMainScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("view/MainScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	public void checkCredentials(ActionEvent event) {
		try {
			if (loginModel.isLogin(usernameTextField.getText(), passwordPasswordField.getText())) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("view/PlayMenu.fxml"));
				root = loader.load();
				PlayMenuControl playMenuControl = (PlayMenuControl) loader.getController();
				playMenuControl.setUserName(usernameTextField.getText());
				stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			}else if(usernameTextField.getText() == "" || passwordPasswordField.getText() == ""){
				isConnected.setText("Username and password cannot be empty.");
			} else {
				isConnected.setText("Username or password is not correct.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			isConnected.setText("Exception occurred! Try again later. ");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (loginModel.isDbConnected()) {
			isConnected.setText("");
		} else {
			isConnected.setText("Not Connected");
		}

	}
}
