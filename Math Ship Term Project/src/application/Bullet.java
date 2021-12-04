package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bullet {

    public boolean toRemove;

		int posX, posY, speed = 10;
		static final int size = 6;
        int score;
        
		public Bullet(int posX, int posY) {
			this.posX = posX;
			this.posY = posY;
		}

		public void update() {
			posY -= speed;
		}

		public void draw(GraphicsContext gc, Color boolet, Color megaboolet) {
			gc.setFill(boolet);
			if (score > 20) {
				gc.setFill(megaboolet);
				speed = 30;
				gc.fillRect(posX, posY, size + 5, size + 15);
			} else {
				gc.fillOval(posX, posY, size, size);
			}
		}

		public boolean colide(Ship Rocket) {
			int distance = distance(this.posX + size / 2, this.posY + size / 2,
					Rocket.posX + Rocket.sizeX / 2, Rocket.posY + Rocket.sizeY / 2);
			return distance < Rocket.sizeX / 2 + size / 2;
		}

        int distance(int x1, int y1, int x2, int y2) { // Distance formula
            return (int) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
        }

        void setSpeed(int score){
            this.score = score;
        }
    
}
