package application;

import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Stars { // This is the background
    int posX, posY;
    int hOval, wOval, r, g, b;
    Random random = new Random();
    
    double opacity;
    
    public Stars() {
    	posX = random.nextInt(500);
    	posY = 0; //start from top of screen
    	hOval = random.nextInt(4)+ 1;
    	wOval = random.nextInt(4)+ 1; 
    	r = random.nextInt(100) + 153;
    	g = random.nextInt(100) + 153;
    	b = random.nextInt(100) + 153;
    	opacity = random.nextFloat();
    	
    	if(opacity < 0) {
    		opacity = opacity * -1;
    	}
    	if(opacity > 0.5) {
    		opacity = 0.5;
    	}
   
    }
    
    public void draw(GraphicsContext gc) {
    	if(opacity > 0.85) {
    		opacity -= 0.01;
    	}
    	if(opacity < 0.1) {
    		opacity+= 0.01;
    	}
    	gc.setFill(Color.rgb(r, g, b, opacity));
    	gc.fillOval(posX, posY, wOval, hOval);
    	posY+=3;
    }
}