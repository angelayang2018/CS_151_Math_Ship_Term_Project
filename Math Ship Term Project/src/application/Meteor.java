package application;

import javafx.scene.image.Image;

public class Meteor extends Ship {

    int speed; 
    int boundary = 700; // temp: find another way to set this boundary 

    public Meteor(int posX, int posY, int sizeX, int sizeY, Image image) {
        super(posX, posY, sizeX, sizeY, image);
    }

    public void update() {
        super.update();
        if (!exploding && !destroyed) {
            posY += speed;
        }
        if (posY > boundary) {
            destroyed = true;
        }
    }

    public void setSpeed(int score){
        speed = (score/1000) + 2; // can change this to adjust difficulty
    }
}
