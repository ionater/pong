import java.awt.*;
public class Ball {
	//declare variables
	private int x, y, cx, cy, speed, size;
	private Color color;
	
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
		}
		else if (y < top) {
			reverseY();
		}
		if(x < 0) {
			reverseX();
		}
		else if (x > 640 - size) {
			reverseX();
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
}
