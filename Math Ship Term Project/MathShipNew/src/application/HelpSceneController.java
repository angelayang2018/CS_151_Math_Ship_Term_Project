package application;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HelpSceneController implements Initializable {

    @FXML
    private ImageView spaceShip1;
    @FXML
    private ImageView heartImages;
    

    private Stage stage;
	private Scene scene;
	private Parent root; 

    private String user;

    public void toPlayMenuScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/PlayMenu.fxml"));
		root = loader.load();
        PlayMenuControl playMenuControl = (PlayMenuControl) loader.getController();
		playMenuControl.setUserName(user);
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

    //@Override
    public void initialize(URL location, ResourceBundle resources) {

        Polyline polyline = new Polyline();
        polyline.getPoints().addAll(new Double[]{
            50.0, 40.0,
            120.0, 40.0, 
            50.0, 40.0, 
            -50.0, 40.0, 
            50.0, 40.0 
        });

        //Left Right Key translation
        PathTransition translate = new PathTransition();
        translate.setNode(spaceShip1);
        translate.setDuration(Duration.seconds(5));
        translate.setPath(polyline);
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.play();   

    }
    public void setUserName(String user){
        this.user = user;
    }  
}
