package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.javafx.robot.FXRobot;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;



public class GameStart implements Initializable
{
	@FXML
	private AnchorPane gamescene;
	
	@FXML
	private ImageView redShip1;
	
	
	@FXML
	private Button startGame;
	
    public void startGame(){
    	startGame.setVisible(false);
    	Ship ship = new Ship(redShip1);
    	Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				run(ship);
			}
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
    	/**
    	gamescene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			  public void handle(KeyEvent event){

			    if (event.getCode().toString().equals("RIGHT")) {
			        ship.setLayoutX(ship.getLayoutX() + 10);
			    } else if (event.getCode().toString().equals("LEFT")) {
			        ship.setLayoutX(ship.getLayoutX() - 10);
			    }
			  }
			});
			**/
    	
    	

    }
    
    private void run(Ship ship) {
    	gamescene.setOnKeyPressed(e -> {
    		switch (e.getCode()) {
    		case A:
    			ship.moveLeft();
    			break;
    		case D:
    			ship.moveRight();
    			break;
			default:
				break;
    		}
    	});

	}
    
	public void moveShip(KeyEvent event) {
		/**
		Bounds bounds = gamescene.localToScreen(gamescene.getBoundsInLocal());
		double sceneXPos = bounds.getMinX();
		
		double xPos = e.getX();
		double shipWidth = ship.getWidth();
		
		if(xPos >= sceneXPos + (shipWidth/2) && xPos <= (sceneXPos + gamescene.getWidth() - (shipWidth/2))){
			ship.setLayoutX(xPos - sceneXPos - (shipWidth/2));
		} else if (xPos < sceneXPos + (shipWidth/2)) {
			ship.setLayoutX(0);
		} else if (xPos > (sceneXPos + gamescene.getWidth()) - (shipWidth/2)) {
			ship.setLayoutX(gamescene.getWidth() - shipWidth);
		}
		**/
		gamescene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			  public void handle(KeyEvent event){

			    if (event.getCode().toString().equals("RIGHT")) {
			        ship.setLayoutX(ship.getLayoutX() + 10);
			    } else if (event.getCode().toString().equals("LEFT")) {
			        ship.setLayoutX(ship.getLayoutX() - 10);
			    }
			  }
			});
		
        
	}
    public void shootBullet() {
    	
        
	}
    /**
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent e) {
            //gamescene.setOnKeyPressed(e ->)
        	moveShip(e);
        }}));
    **/
    

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//timeline.setCycleCount(Animation.INDEFINITE);
		
	}
    

	
	/*
	private static final int WIDTH = 500;
	private static final int HEIGHT = 700;
	private static final int PLAYER_SIZE = 50;
	public GraphicsContext gc;
	static final Image PLAYER_IMG = new Image("RedFlyShip.png");
	Ship player;
	private Stage stage;
	private Scene scene;
    /**
	public void startGame(ActionEvent event) throws Exception {
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

		start(stage);
	}
	
	
	public void start(Stage stage) throws Exception{
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		gc = canvas.getGraphicsContext2D();
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				run(gc);
			}
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
		
		setup();
		stage.setScene(new Scene(new StackPane(canvas)));
		stage.setTitle("Math Ship");
		stage.show();
		
	}
	
	private void setup() {
		player = new Ship(WIDTH/2, HEIGHT - PLAYER_SIZE, PLAYER_SIZE, PLAYER_IMG);
		
	}
	
	private void run(GraphicsContext gc) {
		gc.setFill(Color.grayRgb(20));
		
		//player.update();
		player.draw();
		player.posX = 250;
	}*/
}
