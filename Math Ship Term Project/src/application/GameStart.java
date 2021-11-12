package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameStart
{
	
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
	**/
	
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
	}
}
