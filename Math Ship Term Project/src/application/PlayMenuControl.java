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
	private static final Random RAND = new Random();
	private static final int WIDTH = 500; // width and height of canvas
	private static final int HEIGHT = 700;
	private static final int PLAYER_SIZE = 50; // this will be basically the hit box of player
	private Pos positions = new Pos(0, 0);
	private static int score;
	private static int inequality;
	public GraphicsContext gc;
	private static int lives;

	static final Image PLAYER_IMG = new Image("https://i.ibb.co/PD1KSQp/Red-Fly-Ship.png");
	static final Image REDMETEOR = new Image("https://i.ibb.co/k63WNzJ/Multiply-Fire.png");
	static final Image BLUEMETEOR = new Image("https://i.ibb.co/5r8zbBF/divideball.png");
	static final Image PINKMETEOR = new Image("https://i.ibb.co/HTsc3zQ/AddBall.png");
	static final Image GREENMETEOR = new Image("https://i.ibb.co/gZLqLGM/Subtract-Ball.png");
	static final Image background = new Image("https://i.ibb.co/tpRfC5H/CommonBG.png"); // need to work on background
	static final Image THREELIVES = new Image("https://i.ibb.co/vPj4Hyg/3Heart.png");
	static final Image TWOLIVES = new Image("https://i.ibb.co/26mfSMt/2Heart.png");
	static final Image ONELIVE = new Image("https://i.ibb.co/LxwRR87/1heart.png");
	static final Image PLAYER_IMG_PINK = new Image("https://i.ibb.co/cL3fXLN/PINKSHIP.png");

	static final Image METEOR_IMGS[] = { PINKMETEOR, GREENMETEOR }; // Meteor Images in array
	final int MAX_METEORS = 10, MAX_BULLETS = MAX_METEORS * 2;
	boolean gameOver = false;

	@FXML
	Label usernameTextField;

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
			if ((gameOver && lives == 1) || inequality > 10) {
				gameOver = false;
				setup();
			}
		});

		ArrayList<String> keyPressedlist = new ArrayList<String>();
		canvas.setFocusTraversable(true);
		canvas.setOnKeyPressed((KeyEvent e) -> {
			String keyName = e.getCode().toString();
			// avoid duplicates to list
			if (!keyPressedlist.contains(keyName)) {
				keyPressedlist.add(keyName);
				if (keyPressedlist.contains("ESCAPE")) {
					timeline.pause();
					gc.setFont(Font.font(50));
					gc.setFill(Color.RED);
					gc.fillText("PAUSED", 250, 350);
					gc.setFont(Font.font(25));
					gc.fillText("Press ESC again to unpause``", 250, 370);
				}
			} else if (keyPressedlist.contains("ESCAPE")) {
				keyPressedlist.remove("ESCAPE");
				timeline.play();
			}
		});

		setup();
		stage.setScene(mainScene);
		stage.setResizable(false);
		stage.show();
	}
	
	//END OF startGame()------------------------------------------------------------------------------

	private void run(GraphicsContext gc) {
		gc.setFill(Color.grayRgb(20));

		gc.fillRect(0, 0, WIDTH, HEIGHT);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFont(Font.font(15));
		gc.setFill(Color.WHITE);
		gc.fillText("Score: " + score, 60, 20);
		gc.setFont(Font.font(30));
		gc.setFill(Color.YELLOW);
		gc.fillText(inequality + " < 10", 250, 40);

		if (inequality > 10 || (gameOver && lives == 1)) {
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
			player.update();
			player.draw(gc); // draws the players image

			if (positions.x < WIDTH - PLAYER_SIZE) {// this is here so that the player can't move off screen
				player.posX = (int) positions.x;
				player.posY = (int) positions.y;
			}

			meteors.stream().peek(Ship::update).forEach(e -> { // checks if player collides
				e.draw(gc);
				e.setSpeed(score);
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
				shot.update();
				shot.draw(gc);

				for (Meteor bomb : meteors) { // METEOR MATH COLLIDING LOGIC PINK(add) RED(MULT) GREEN(SUB)
					if (shot.colide(bomb) && !bomb.exploding) {
						if (bomb.img == PINKMETEOR) {
							inequality++;
							score++;
						}
						else if (bomb.img == BLUEMETEOR && inequality / 2 >= 1) {
							inequality /= 2;
						}
						else if (bomb.img == REDMETEOR) {
							inequality *= 2;
						}
						else if (bomb.img == GREENMETEOR && inequality - 1 >= 1) {
							inequality--;
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

		}
	}

	private void setup() {
		bullets = new ArrayList<>();
		meteors = new ArrayList<>();
		player = new Ship(250, 650, PLAYER_SIZE, PLAYER_SIZE, PLAYER_IMG_PINK);
		score = 0;
		inequality = 1;
		lives = 3;
		IntStream.range(0, MAX_METEORS).mapToObj(i -> this.newBomb()).forEach(meteors::add);
	}

	private void reset() {
		bullets = new ArrayList<>();
		meteors = new ArrayList<>();
		player = new Ship(250, 650, PLAYER_SIZE, PLAYER_SIZE, PLAYER_IMG_PINK);
		inequality = 1;
		IntStream.range(0, MAX_METEORS).mapToObj(i -> this.newBomb()).forEach(meteors::add);
	}

	public Meteor newBomb() { // generates random bomb at a random x location
		return new Meteor(50 + RAND.nextInt(WIDTH - 100), 0, 50, 80, METEOR_IMGS[RAND.nextInt(METEOR_IMGS.length)]);
	}

	public void toScoreboard(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("view/ScoreboardScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	public void toHelp(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("view/HelpScene.fxml"));
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
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void getUser(String user) {
		usernameTextField.setText(user);

	}

}
