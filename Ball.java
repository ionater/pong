import java.awt.*;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class Ball {
	//declare variables
	private int x, y, cx, cy, speed, size;
	private Color color;
	static final int MAX_SPEED = 20;
	
	
	
	//ball constructor
	public Ball(int x, int y, int cx, int cy, int speed, Color color, int size) {
		this.x = x;
		this.y = y;
		this.cx = cx;
		this.cy = cy;
		this.speed = speed;
		this.color = color;
		this.size = size;
	}
	
	public void playWallSFX()
	{
		try
		{
		AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File("WallHit.wav"));
		Clip Paddle = AudioSystem.getClip();
		Paddle.open(audioInput);
		Paddle.start();
		}
		catch (Exception e)
		{
			
			System.out.println(e);
			
		}
	}
	
	public void paint(Graphics g) {
		//set brush color to ball color
		g.setColor(color);
		
		//paint ball
		g.fillOval(x, y, size, size);
	}
	public void moveBall() {
		x += cx;
		y += cy;
	}
	public void bounceOffEdges(int top, int bottom) {
		if(y > bottom - size) {
			reverseY(); 
			playWallSFX();
		}
		else if (y < top) {
			reverseY();
			playWallSFX();
		}
	}
	
	public void increaseSpeed(){
        //make sure current speed is less than max speed before incrementing
        if(speed < MAX_SPEED){
            //increase the speed by one
            speed+=2;
            
            //assign speed
            cx = (cx / Math.abs(cx)*speed);
            cy = (cy / Math.abs(cy)*speed);

        }

    }
	
	public void reverseX() {
		cx *= -1;
	}
	public void reverseY() {
		cy *= -1;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public void setX(int i) {
		x = i;
	}
	public void setY(int i ) {
		y = i;
	}
	public void setSpeed(int i) {
		speed = i;
	}
	public void setCx(int i ) {
		cx = i;
	}
	public void setCy(int i ) {
		cy = i;
	}
	
	
}
