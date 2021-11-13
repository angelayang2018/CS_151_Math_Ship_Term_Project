package application;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ship implements GameObject {
	/**
	int posX, posY, size;
	Image img;
	
    
	public Ship(int posX, int posY, int size, Image image) {
		this.posX = posX;
		this.posY = posY;
		this.size = size;
		this.img = image;
	}
	
	public void draw() {
		gc.drawImage(img, posX, posY, size, size);
	}
	
	/*public Bullet shoot() {
		return new Bullet(posX + size/2 - Bullet.size /2, posY - Bullet.size);
	}
	**/
	@FXML
	public ImageView redShip;
	
	private double posX;
	private double posY;
	
	
	
	public Ship(ImageView image) {
		this.redShip = image;
		this.posX = redShip.getX();
		this.posY = redShip.getY();
	}
	
	public void setImage(ImageView image) {
		this.redShip = image;
	}
	
	public double getWidth() {
		return redShip.getFitWidth();
	}
	public void setLayoutX(double d) {
		// TODO Auto-generated method stub
		redShip.setX(d);
	}
	public double getLayoutX() {
		// TODO Auto-generated method stub
		return redShip.getX();
	}
	
	public void update() {
		
	}
	
	public void moveRight() {
		redShip.setLayoutX(getLayoutX() + 20);
	}
	public void moveLeft() {
		redShip.setLayoutX(getLayoutX() - 20);
	}
	
	


	
	
	
	
	
	
	
	

}
