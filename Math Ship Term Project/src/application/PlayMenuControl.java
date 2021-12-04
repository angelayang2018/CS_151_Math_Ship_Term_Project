package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PlayMenuControl implements Initializable {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private Ship player;
	private List<Bullet> bullets;
	private List<Meteor> meteors;
	private List<Stars> stars; //Stars in the background
    private static final Random RAND = new Random();
	private static final int WIDTH = 500; // width and height of canvas
	private static final int HEIGHT = 700;
	private static final int PLAYER_SIZE = 50; // this will be basically the hit box of player
	private Pos positions = new Pos(250, 700);
	private static int score;
	private static int inequality;
	private static int difficulty;
	public GraphicsContext gc;
	private static int lives;
	static Color boolet = Color.RED;
    static Color megaboolet = Color.DARKRED;


	static Image PLAYER_IMG = new Image("https://i.ibb.co/PD1KSQp/Red-Fly-Ship.png");
	static final Image REDMETEOR = new Image("https://i.ibb.co/k63WNzJ/Multiply-Fire.png");
	static final Image BLUEMETEOR = new Image("https://i.ibb.co/5r8zbBF/divideball.png");
	static final Image PINKMETEOR = new Image("https://i.ibb.co/HTsc3zQ/AddBall.png");
	static final Image GREENMETEOR = new Image("https://i.ibb.co/gZLqLGM/Subtract-Ball.png");
	static final Image background = new Image("https://i.ibb.co/tpRfC5H/CommonBG.png"); // need to work on background
	static final Image THREELIVES = new Image("https://i.ibb.co/vPj4Hyg/3Heart.png");
	static final Image TWOLIVES = new Image("https://i.ibb.co/26mfSMt/2Heart.png");
	static final Image ONELIVE = new Image("https://i.ibb.co/LxwRR87/1heart.png");

	static final Image METEOR_IMGS[] = { PINKMETEOR, GREENMETEOR, REDMETEOR, BLUEMETEOR }; // Meteor Images in array
	final int MAX_METEORS = 10, MAX_BULLETS = MAX_METEORS * 2;
	boolean gameOver = false;

	@FXML
	Label usernameLabel;

	private String user;
	
	public void blueship(ActionEvent event) throws IOException {
		PLAYER_IMG = new Image("https://i.ibb.co/16M4r94/HAROONPURRPLE.png");
		boolet = Color.ALICEBLUE;
		megaboolet = Color.AQUAMARINE;
		startGame(event);
	}
	
	public void pinkship(ActionEvent event) throws IOException {
		PLAYER_IMG = new Image("https://i.ibb.co/cL3fXLN/PINKSHIP.png");
		boolet = Color.PINK;
		megaboolet = Color.HOTPINK;
		startGame(event);
	}
	
	public void redship(ActionEvent event) throws IOException {
		PLAYER_IMG = new Image("https://i.ibb.co/PD1KSQp/Red-Fly-Ship.png");
		boolet = Color.RED;
		megaboolet = Color.DARKRED;
		startGame(event);
	}
	
	public void greenship(ActionEvent event) throws IOException {
		PLAYER_IMG = new Image("https://i.ibb.co/gFwQHcS/khuong.png");
		boolet = Color.GREENYELLOW;
		megaboolet = Color.FORESTGREEN;
		startGame(event);
	}
	
	public void startGame(ActionEvent event) throws IOException {

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle("Math Ship");
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		gc = canvas.getGraphicsContext2D();
		BorderPane root1 = new BorderPane(canvas);
		Scene mainScene = new Scene(root1, WIDTH, HEIGHT);

		// Created time line object with Duration set at 10 ms. it will run the Graphics
		// Context
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> run(gc)));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();

		canvas.setCursor(Cursor.MOVE);

		canvas.setOnMouseMoved(e -> positions.set(e.getX(), e.getY()));
		canvas.setOnMousePressed(e -> {
			if (bullets.size() < MAX_BULLETS) { // shots cannot exceed twice the amount of enemy objects
				bullets.add(player.shoot());
			}
			if ((gameOver && lives == 1) || inequality > 50) {
				gameOver = false;
				setup();
			}
		});

		ArrayList<String> keyPressedlist = new ArrayList<String>();
		keyPressedlist.add("ESCAPE");
		keyPressedlist.add("TAB");
		keyPressedlist.add("SHIFT");

		canvas.setFocusTraversable(true);
		canvas.setOnKeyPressed((KeyEvent e) -> {
		String keyName = e.getCode().toString();
			// avoid duplicates to list
			
				if (keyName.equals("ESCAPE")) {
					timeline.pause();
					gc.setFill(Color.BLACK);

					gc.fillRect(100,  300, 300, 200);
					gc.setFont(Font.font(50));
					gc.setFill(Color.RED);
					gc.fillText("PAUSED", 250, 350);
					gc.setFont(Font.font(25));
					gc.setFill(Color.WHITE);
					gc.fillText("Enter to unpause", 250, 380);
					
					gc.setFont(Font.font(25));
					gc.setFill(Color.WHITE);

					gc.fillText("SHIFT to ship select", 250, 410);
					gc.setFont(Font.font(25));
					gc.setFill(Color.WHITE);

					gc.fillText("TAB to menu", 250, 440);
					
				}
				
				
			
			
			else if (keyName.equals("ENTER")) {
				timeline.play();
			}
			else if(keyName.equals("TAB")) {
				timeline.stop();
				try {
					
					root = FXMLLoader.load(getClass().getResource("view/MainScene.fxml"));

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				scene = new Scene(root);
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			}
			else if(keyName.equals("SHIFT")) {
				timeline.stop();
				try {
					
					root = FXMLLoader.load(getClass().getResource("view/CharacterSelect.fxml"));

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				scene = new Scene(root);
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			}else if(keyName.equals("SPACEBAR")) {
				if(gameOver) {
					gameOver = false;
					
					
				}
			}
			
			
		});

		setup();
		stage.setScene(mainScene);
		stage.setResizable(false);
		stage.show();
	}

	private void run(GraphicsContext gc) {
		gc.setFill(Color.grayRgb(20));

		gc.fillRect(0, 0, WIDTH, HEIGHT);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFont(Font.font(15));
		gc.setFill(Color.WHITE);
		gc.fillText("Score: " + score, 60, 20);
		gc.setFont(Font.font(30));
		gc.setFill(Color.YELLOW);
		gc.fillText(inequality + " < 50", 250, 40);

		if (inequality > 50 || (gameOver && lives == 1)) {
			gameOver = true;
			gc.setFont(Font.font(35));
			gc.setFill(Color.RED);
			gc.fillText("Game Over \n Your Score: " + score + "\n\n Click to Play Again", WIDTH / 2, HEIGHT / 2.5);

		}

		else if (gameOver && lives > 0) { // If player dies or makes the inequality false
			reset();
			gameOver = false;
			lives--;
		} else {
			if (lives == 3) {
				gc.drawImage(THREELIVES, 350, 10);
			} else if (lives == 2) {
				gc.drawImage(TWOLIVES, 400, 10);
			} else if (lives == 1) {
				gc.drawImage(ONELIVE, 400, 10);
			}
			
			//stars.forEach(Stars::draw(gc));
			for( Stars star : stars) {
				star.draw(gc);
			}
			player.update();
			player.draw(gc); // draws the players image

			if (positions.x < WIDTH - PLAYER_SIZE) {// this is here so that the player can't move off screen
				player.posX = (int) positions.x;
				player.posY = (int) positions.y;
			}
			

			meteors.stream().peek(Ship::update).forEach(e -> { // checks if player collides
				e.draw(gc);
				e.setSpeed(difficulty);
				if (player.collide(e) && !player.exploding) {
					player.explode();
				}
			});

			for (int i = bullets.size() - 1; i >= 0; i--) {
				Bullet shot = bullets.get(i);
				if (shot.posY < 0 || shot.toRemove) { // checks if bullet collides or if it goes off screen
					bullets.remove(i);
					continue;
				}
				shot.setSpeed(score);
				shot.update();
				shot.draw(gc, boolet, megaboolet);

				for (Meteor bomb : meteors) { // METEOR MATH COLLIDING LOGIC PINK(add) RED(MULT) GREEN(SUB)
					if (shot.colide(bomb) && !bomb.exploding) {
						if (bomb.img == PINKMETEOR) {
							inequality++;
							score++;
							difficulty+=2;
						}
						else if (bomb.img == BLUEMETEOR && inequality / 2 >= 1) {
							inequality /= 2;
							difficulty+=2;
							score++;
						}
						else if (bomb.img == REDMETEOR) {
							inequality *= 2;
							score = score + 50;
							difficulty+=2;
						}
						else if (bomb.img == GREENMETEOR && inequality - 1 >= 1) {
							inequality--;
							difficulty+=2;
							score++;
						}
					
						bomb.explode();
						shot.toRemove = true;
					}
				}
			}
			for (int i = meteors.size() - 1; i >= 0; i--) { // go through meteor list if it is destroyed create a new one
				if (meteors.get(i).destroyed) {
					meteors.set(i, newBomb());
				}
			}
			gameOver = player.destroyed; // if player is destroyed then the game is over

			stars.add(new Stars());
			
			for(int i = 0; i < stars.size(); i++) {
				if(stars.get(i).posY > HEIGHT) {
					stars.remove(i); //removes from list if stars goes off screen.
				}
			}
		}
	}

	private void setup() {
		bullets = new ArrayList<>();
		meteors = new ArrayList<>();
		stars = new ArrayList<>();
		player = new Ship(250, 650, PLAYER_SIZE, PLAYER_SIZE, PLAYER_IMG);
		score = 0;
		inequality = 1;
		difficulty = 0;
		lives = 3;
		IntStream.range(0, MAX_METEORS).mapToObj(i -> this.newBomb()).forEach(meteors::add);
	}

	private void reset() {
		bullets = new ArrayList<>();
		meteors = new ArrayList<>();
		player = new Ship(250, 650, PLAYER_SIZE, PLAYER_SIZE, PLAYER_IMG);
		inequality = 1;
		IntStream.range(0, MAX_METEORS).mapToObj(i -> this.newBomb()).forEach(meteors::add);
	}

	public Meteor newBomb() { // generates random bomb at a random x location
		return new Meteor(50 + RAND.nextInt(WIDTH - 100), 0, 50, 80, METEOR_IMGS[RAND.nextInt(METEOR_IMGS.length)]);
	}

	public void toScoreboard(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("view/ScoreboardScene.fxml"));
		root = loader.load();
		ScoreBoardControl scoreBoardControl= (ScoreBoardControl) loader.getController();
		scoreBoardControl.setUserName(user);
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	public void toHelp(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("view/HelpScene.fxml"));
		root = loader.load();
		HelpSceneController helpSceneController = (HelpSceneController) loader.getController();
		helpSceneController.setUserName(user);
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	public void toMainScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("view/MainScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources){

	}

	public void setUserName(String user) {
		if(user != null){
			usernameLabel.setText(user);
			this.user = user;
		}
	}
}
