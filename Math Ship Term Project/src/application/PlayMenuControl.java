package application;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

//import application.PlayMenuControl.Rocket;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.KeyEvent;


public class PlayMenuControl {
	private Stage stage;
	private Scene scene;
	private Parent root; 
	Ship player;
	List<Shot>shots;
	List<Meteor>Meteors;
	private static final Random RAND = new Random();
	private static final int WIDTH = 500;
	private static final int HEIGHT = 700;
	private static final int PLAYER_SIZE = 50;

	private double mouseX;
	private double mouseY;
	static int score;
	static int inequality;
	public GraphicsContext gc;
    static final Image PLAYER_IMG = new Image("https://i.ibb.co/PD1KSQp/Red-Fly-Ship.png");
    static final Image REDMETEOR = new Image("https://i.ibb.co/k63WNzJ/Multiply-Fire.png");
    static final Image BLUEMETEOR = new Image("https://i.ibb.co/5r8zbBF/divideball.png");
    static final Image PINKMETEOR = new Image("https://i.ibb.co/HTsc3zQ/AddBall.png");
    static final Image GREENMETEOR = new Image("https://i.ibb.co/gZLqLGM/Subtract-Ball.png");
    static final Image background = new Image("https://i.ibb.co/tpRfC5H/CommonBG.png");
    //ImageView bg = new ImageView(background);
    
    static final Image BOMBS_IMG[]= {
    		REDMETEOR, BLUEMETEOR, PINKMETEOR, GREENMETEOR
    };
	final int MAX_BOMBS = 10,  MAX_SHOTS = MAX_BOMBS * 2;
	boolean gameOver = false;
	
	public void startGame(ActionEvent event) throws IOException {
		
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setTitle("Math Ship");

        Canvas canvas = new Canvas(500, 700);
        gc = canvas.getGraphicsContext2D();

        BorderPane root = new BorderPane(canvas);
        Scene mainScene = new Scene(root, 500, 700);
       
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> run(gc)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        canvas.setCursor(Cursor.MOVE);
        canvas.setOnMouseMoved(e -> mouseX = e.getX()); //gets positions of x of mouse cursor
        canvas.setOnMousePressed(e -> {
			if(shots.size() < MAX_SHOTS) { //shots cannot exceed twice the amount of enemy objects
				shots.add(player.shoot()); 
			}
			if(gameOver) { 
				gameOver = false;
				setup();
			}
		});
        
        setup();
        stage.setScene(mainScene);
        stage.setResizable(false);
        stage.show();
	}
	
	private void run(GraphicsContext gc) {
	    
		gc.setFill(Color.grayRgb(20));
		//gc.drawImage(background, 0, 0);

		//Temporary. ToDO: NEED TO ADD BACKGROUND
		gc.fillRect(0, 0, WIDTH, HEIGHT);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFont(Font.font(15));
		gc.setFill(Color.WHITE);
		gc.fillText("Score: " + score, 60,  20);
		gc.fillText("Inequality: " + inequality + " < 100", 250, 20);
		
		if(gameOver || inequality > 100) { //If player dies or makes the inequality false
			gameOver = true;
			gc.setFont(Font.font(35));
			gc.setFill(Color.YELLOW);
			gc.fillText("Game Over \n Your Score: " + score + "\n\n Click to Play Again", WIDTH / 2, HEIGHT /2.5);
			
		}
		else {
			playGame();
		}
	}//end run()
	
	private void playGame(){
		player.update();
		player.draw();
		

		if(mouseX < WIDTH - PLAYER_SIZE) {
		player.posX = (int) mouseX;
		}
		
		Meteors.stream().peek(Ship::update).peek(Ship::draw).forEach(e -> {
			if(player.colide(e) && !player.exploding) {
				player.explode();
			}
		});
		
		for (int i = shots.size() - 1; i >=0 ; i--) {
			Shot shot = shots.get(i);
			if(shot.posY < 0 || shot.toRemove)  { 
				shots.remove(i);
				continue;
			}
			shot.update();
			shot.draw();
			
			for (Meteor bomb : Meteors) {
				if(shot.colide(bomb) && !bomb.exploding && bomb.img == PINKMETEOR) {
					inequality++;
					score++;
					bomb.explode();
					shot.toRemove = true;
				}
				else if(shot.colide(bomb) && !bomb.exploding && bomb.img == BLUEMETEOR) {
					if(inequality/2 >= 1) {
					inequality = inequality/2;
					}
					score++;
					bomb.explode();
					shot.toRemove = true;
				}
				else if(shot.colide(bomb) && !bomb.exploding && bomb.img == REDMETEOR) {
					inequality = inequality*2;
					score++;
					bomb.explode();
					shot.toRemove = true;
				}
				else if(shot.colide(bomb) && !bomb.exploding && bomb.img == GREENMETEOR) {
					if(inequality- 1 >= 1) {
						inequality--;
					}
					score++;
					bomb.explode();
					shot.toRemove = true;
				}
			}
		}
		for (int i = Meteors.size() - 1; i >= 0; i--){  
			if(Meteors.get(i).destroyed)  {
				Meteors.set(i, newBomb());
			}
		}
		gameOver = player.destroyed;
	}//end playGame()
	
	private void setup() {
		shots = new ArrayList <>();
		Meteors = new ArrayList<>();
        player = new Ship(250, 650, PLAYER_SIZE, PLAYER_SIZE, PLAYER_IMG);
        score = 0;
        inequality = 1;
		IntStream.range(0, MAX_BOMBS).mapToObj(i -> this.newBomb()).forEach(Meteors::add);

	}
	public class Ship{

		int posX, posY, sizeX, sizeY;
		Image img;
		boolean exploding, destroyed;
		
		public Ship(int posX, int posY, int sizeX, int sizeY, Image image) {
			this.posX = posX;
			this.posY = posY;
			this.sizeX = sizeX;
			this.sizeY = sizeY;
			img = image;
		}
		public void update() {
			if(exploding) {
				destroyed = true;
			}
		}
		public void draw() {

				gc.drawImage(img, posX, posY, sizeX, sizeY);
			
		}
		
		public Shot shoot() {
			return new Shot(posX + sizeX / 2 - Shot.size / 2, posY - Shot.size);
		}
		
		public boolean colide(Ship other) {
			int d = distance(this.posX + sizeX / 2, this.posY + sizeY /2, 
							other.posX + other.sizeX / 2, other.posY + other.sizeY / 2);
			return d < other.sizeX / 2 + this.sizeX / 2 ;
		}
		public void explode() {
			exploding = true;
			
		}
	}

	public class Meteor extends Ship {
		
		int SPEED = (score/100)+2;
		
		public Meteor(int posX, int posY, int sizeX, int sizeY, Image image) {
			super(posX, posY, sizeX, sizeY, image);
		}
		
		public void update() {
			super.update();
			if(!exploding && !destroyed) {
				posY += SPEED;
			}
			if(posY > HEIGHT) {
				
				destroyed = true;
			}
		}
	}
	
	public class Shot{
		
		public boolean toRemove;

		int posX, posY, speed = 10;
		static final int size = 6;
			
		public Shot(int posX, int posY) {
			this.posX = posX;
			this.posY = posY;
		}

		public void update() {
			posY-=speed;
		}
		

		public void draw() {
			gc.setFill(Color.RED);
			gc.fillOval(posX, posY, size, size);
			
		}
		
		public boolean colide(Ship Rocket) {
			int distance = distance(this.posX + size / 2, this.posY + size / 2, 
					Rocket.posX + Rocket.sizeX / 2, Rocket.posY + Rocket.sizeY / 2);
			return distance  < Rocket.sizeX / 2 + size / 2;
		}

	}
	
	public Meteor newBomb() {
		return new Meteor(50 + RAND.nextInt(WIDTH - 100), 0, 50, 80, BOMBS_IMG[RAND.nextInt(BOMBS_IMG.length)]);
	}
	
	
	int distance(int x1, int y1, int x2, int y2) {
		return (int) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
	}
	

	
	//Scene switchers below: 
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
