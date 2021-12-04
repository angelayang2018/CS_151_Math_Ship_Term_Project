package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Ship {

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
			if (exploding) {
				destroyed = true;
			}
		}

		public void draw(GraphicsContext gc) {
			gc.drawImage(img, posX, posY, sizeX, sizeY);

		}

		public Bullet shoot() {
			return new Bullet(posX + sizeX / 2 - Bullet.size / 2, posY - Bullet.size);
		}

		public boolean collide(Ship other) {
			int d = distance(this.posX + sizeX / 2, this.posY + sizeY / 2,
					other.posX + other.sizeX / 2, other.posY + other.sizeY / 2);
			return d < other.sizeX / 2 + this.sizeX / 2;
		}

		public void explode() {
			exploding = true;

		}

        int distance(int x1, int y1, int x2, int y2) { // Distance formula
            return (int) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
        }


    
}
