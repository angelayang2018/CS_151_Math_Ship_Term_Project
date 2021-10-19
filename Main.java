import javafx.application.Application;

import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class Main extends Application{
	
	Stage window;
	Scene loginScene, playScene;
	
	
	Button button;
	
	public static void main(String [] args) {
		launch(args);
	}
	
	@Override 
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		
		Label l1 = new Label("Main Screen"); 
		primaryStage.setTitle("Main Screen");
		Button b1 = new Button("Login");
		Button b2 = new Button("Play");
		b1.setOnAction(e -> window.setScene(loginScene));
		b2.setOnAction(e -> window.setScene(playScene));
		
		VBox layout1 = new VBox(20);
		layout1.getChildren().addAll(l1, b1, b2);
		loginScene = new Scene(layout1, 500, 800);
		
		Canvas canvas = new Canvas(500, 800);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		
		button = new Button();
		button.setText("Click me");
		
		StackPane layout = new StackPane();
	
		
		//Scene scene = new Scene(layout, 500, 800);
		
		
		//primaryStage.setScene(scene);
		window.setScene(new Scene(new StackPane(canvas)));
		gc.fillRect(250, 700, 15, 30 );
		primaryStage.show();
	}

}

