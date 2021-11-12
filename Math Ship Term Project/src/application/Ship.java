package application;

import javafx.scene.image.Image;

public class Ship extends GameStart {
	
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
	}*/
	
	
	
	

}
